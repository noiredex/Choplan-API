package com.choplan.reservation.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.reservation.dto.DepositIntentResponse;
import com.choplan.reservation.dto.PaymentConfirmRequest;
import com.choplan.reservation.service.PaymentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposit-intent")
    public ResponseEntity<DepositIntentResponse> createDepositIntent(HttpSession session) {
        Long reservationId = (Long) session.getAttribute("reservationId");
        if (reservationId == null)
            throw new IllegalStateException("No reservation in session");
        return ResponseEntity.ok(paymentService.createDepositIntent(reservationId));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@Valid @RequestBody PaymentConfirmRequest req) {
        try {
            paymentService.confirm(req.getImpUid(), req.getMerchantUid(), req.getReservationId());
            return ResponseEntity.ok("보증금 결제가 확인되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("결제 확인 실패: " + e.getMessage());
        }
    }
}
