package com.choplan.reservation.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choplan.reservation.domain.Payment;
import com.choplan.reservation.domain.Reservation;
import com.choplan.reservation.dto.DepositIntentResponse;
import com.choplan.reservation.dto.PaymentConfirmRequest;
import com.choplan.reservation.repository.PaymentRepository;
import com.choplan.reservation.repository.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PortOneClient portOneClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public DepositIntentResponse createDepositIntent(Long reservationId) {
        int amount = 1000; // 보증금 1,000원으로 변경 (최소 결제 금액 준수)
        String merchantUid = "RSV-" + reservationId + "-DEPOSIT-" + UUID.randomUUID();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        Payment payment = Payment.builder()
                .reservation(reservation)
                .merchantUid(merchantUid)
                .amount(amount)
                .status("READY")
                .build();
        paymentRepository.save(payment);

        return DepositIntentResponse.builder()
                .merchantUid(merchantUid)
                .amount(amount)
                .name("예약 보증금")
                .build();
    }

    @Transactional
    public void confirm(PaymentConfirmRequest req) {
        String token = portOneClient.getAccessToken();
        Map<String, Object> pay = portOneClient.getPaymentByImpUid(token, req.getImpUid());

        if (pay == null) {
            throw new IllegalArgumentException("PortOne payment not found by impUid");
        }

        int amount = ((Number) pay.get("amount")).intValue();
        String status = (String) pay.get("status");
        String payMethod = (String) pay.get("pay_method");
        String receiptUrl = (String) pay.get("receipt_url");
        String pgMerchantUid = (String) pay.get("merchant_uid");

        if (!"paid".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Payment no paid: " + status);
        }

        if (amount != 1_000) {
            throw new IllegalArgumentException("amount mismatch");
        }

        paymentRepository.findByImpUid(req.getImpUid()).ifPresent(existing -> {
            return;
        });

        Payment payment = paymentRepository.findByMerchantUid(req.getMerchantUid()).orElseGet(() -> {
            Reservation reservation = reservationRepository.findById(req.getReservationId())
                    .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
            return Payment.builder()
                    .reservation(reservation)
                    .merchantUid(pgMerchantUid)
                    .amount(amount)
                    .status("READY")
                    .build();
        });

        payment.setImpUid(req.getImpUid());
        payment.setStatus("PAID");
        payment.setMethod(payMethod);
        payment.setProvider("portone");
        payment.setPaidAt(LocalDateTime.now());
        payment.setReceiptUrl(receiptUrl);
        try {
            payment.setRawPayload(objectMapper.writeValueAsString(pay));
        } catch (Exception e) {
        }

        Reservation r = payment.getReservation();
        r.setStatus("PAID");
    }
}
