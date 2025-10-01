package com.choplan.reservation.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choplan.reservation.domain.Payment;
import com.choplan.reservation.domain.Reservation;
import com.choplan.reservation.dto.DepositIntentResponse;
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
    public DepositIntentResponse createDepositIntent (Long reservationId) {
        int amount = 1; //보증금 설정금액
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
    public void confirm(String impUid, String merchantUid, Long reservationId) throws Exception {
        String token = portOneClient.getAccessToken();
        Map<String, Object> pay = portOneClient.getPaymentByImpUid(token, impUid);

        int amount = (int) ((Number) pay.get("amount")).intValue();
        String status = (String) pay.get("status");
        String payMethod = (String) pay.get("pay_method");
        String receipUrl = (String) pay.get("receipt_url");

        Payment payment = paymentRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        if (payment.getAmount() != amount) {
            throw new IllegalArgumentException("Amount mismatch");
        }
        if (!"paid".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Payment not paid: " + status);
        }

        payment.setImpUid(impUid);
        payment.setStatus("PAID");
        payment.setMethod(payMethod);
        payment.setProvider("portone");
        payment.setPaidAt(LocalDateTime.now());
        payment.setReceiptUrl(receipUrl);
        payment.setRawPayload(objectMapper.writeValueAsString(pay));

        Reservation r = payment.getReservation();
        r.setStatus("PAID");
    }
}
