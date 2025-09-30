package com.team.project.service.api;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.project.dto.payment.PaymentCancelRequest;
import com.team.project.dto.payment.PaymentConfirmRequest;
import com.team.project.dto.payment.PaymentIntentRequest;
import com.team.project.dto.payment.PaymentIntentResponse;
import com.team.project.entity.payment.Deposit;
import com.team.project.entity.payment.DepositStatus;
import com.team.project.entity.payment.Payment;
import com.team.project.entity.payment.PaymentStatus;
import com.team.project.payment.PortOneClient;
import com.team.project.repository.DepositRepository;
import com.team.project.repository.PaymentRepository;
import static org.springframework.util.StringUtils.hasText;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final DepositRepository depositRepo;
    private final PortOneClient portOne;

    @Value("${payment.redirect-url}")
    private String redirectUrl;
    @Value("${payment.webhook-url}")
    private String webhookUrl;

    @Transactional
    public PaymentIntentResponse createDepositIntent(PaymentIntentRequest req, String name, String email,
            String tel) {
        if (req.getReservationId() == null || req.getReservationId().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RESERVATION_ID_REQUIRED");

        if (req.getStoreId() == null || req.getStoreId().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "STORE_ID_REQUIRED");

        if (req.getDepositAmount() == null || req.getDepositAmount() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AMOUNT_REQUIRED");

        String merchantUid = "dep-" + UUID.randomUUID();

        Payment pay = Payment.builder()
                .reservationId(req.getReservationId())
                .storeId(req.getStoreId())
                .merchantUid(merchantUid)
                .amount(req.getDepositAmount())
                .status(PaymentStatus.READY)
                .build();
        paymentRepo.save(pay);

        Deposit d = new Deposit();
        d.setReservationId(req.getReservationId());
        d.setPayment(pay);
        d.setAmount(req.getDepositAmount());
        d.setStatus(DepositStatus.INITIATED);
        d.setRefundedAmount(0); // 확인
        d.setCreatedAt(Instant.now());
        d.setUpdatedAt(Instant.now());
        depositRepo.save(d);

        Map<String, Object> buyer = new HashMap<>();
        if (hasText(name))
            buyer.put("buyer_name", name);
        if (hasText(email))
            buyer.put("buyer_email", email);
        if (hasText(tel))
            buyer.put("buyer_tel", tel);

        // Map<String, Object> buyer = Map.of(
        // "name", name,
        // "email", email,
        // "tel", tel
        // );
        Map<String, String> portone = Map.of(
                "redirectUrl", redirectUrl,
                "webhookUrl", webhookUrl);

        return PaymentIntentResponse.builder()
                .merchantUid(merchantUid)
                .amount(req.getDepositAmount())
                .currency("KRW")
                .orderName(req.getOrderName())
                .buyer(buyer)
                .portone(portone)
                .build();
    }

    @Transactional
    public Payment confirmDeposit(PaymentConfirmRequest req) {
        Map<String, Object> response = portOne.getPaymentByImpUid(req.getImpUid());
        Map<String, Object> body = (Map<String, Object>) response.get("response");

        String merchantUidFromPG = (String) body.get("merchant_uid");
        Integer amountFromPG = ((Number) body.get("amount")).intValue();
        String status = (String) body.get("status");

        Payment pay = paymentRepo.findByMerchantUid(req.getMerchantUid())
                .orElseThrow(() -> new IllegalArgumentException("merchantUid not found"));

        if (!Objects.equals(pay.getAmount(), amountFromPG)) {
            throw new IllegalStateException("AMOUNT_MISMATCH");
        }

        if (!Objects.equals(pay.getMerchantUid(), merchantUidFromPG)) {
            throw new IllegalStateException("MERCHANT_UID_MISMATCH");
        }

        if ("paid".equalsIgnoreCase(status)) {
            pay.setImpUid(req.getImpUid());
            pay.setStatus(PaymentStatus.PAID);
            pay.setPaidAt(Instant.now());
            pay.setRaw(new ObjectMapper().valueToTree(response).toString());
            paymentRepo.save(pay);

            depositRepo.findByReservationId(req.getReservationId()).ifPresent(d -> {
                d.setStatus(DepositStatus.PAID);
                d.setUpdatedAt(Instant.now());
                depositRepo.save(d);
            });
            return pay;
        } else {
            pay.setStatus(PaymentStatus.FAILED);
            paymentRepo.save(pay);
            throw new IllegalStateException("PAYMENT_NOT_PAID");
        }
    }

    @Transactional
    public Payment cancelPayment(Long paymentId, PaymentCancelRequest req) {
        Payment pay = paymentRepo.findById(paymentId).orElseThrow();
        if (pay.getImpUid() == null)
            throw new IllegalStateException("NO_IMP_UID");

        Map<String, Object> response = portOne.cancelPayment(
                pay.getImpUid(),
                req.getRefundAmount(),
                req.getReason());
        pay.setStatus(
                (req.getRefundAmount() == null || req.getRefundAmount() == pay.getAmount()) ? PaymentStatus.CANCELLED
                        : PaymentStatus.PARTIAL_CANCELLED);
        pay.setCancelledAt(Instant.now());
        pay.setRaw(String.valueOf(response));
        paymentRepo.save(pay);

        depositRepo.findByReservationId(pay.getReservationId()).ifPresent(d -> {
            Integer refunded = req.getRefundAmount() == null ? pay.getAmount() : req.getRefundAmount();
            d.setRefundedAmount(refunded);
            d.setStatus(refunded != null && refunded > 0 ? DepositStatus.CANCELLED : DepositStatus.CANCELLED);
            d.setUpdatedAt(Instant.now());
            depositRepo.save(d);
        });
        return pay;
    }
}
