package com.choplan.payment.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.payment.dto.DepositIntentRequest;
import com.choplan.payment.dto.DepositIntentResponse;
import com.choplan.payment.dto.PaymentConfirmRequest;
import com.choplan.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = { "http://localhost:5173" }, allowCredentials = "true")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposit-intent")
    public ResponseEntity<DepositIntentResponse> createDepositIntent(@Valid @RequestBody DepositIntentRequest req) {
        Long reservationId = req.getReservationId();
        if (reservationId == null)
            throw new IllegalArgumentException("reservationId is required");
        return ResponseEntity.ok(paymentService.createDepositIntent(reservationId));
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@Valid @RequestBody PaymentConfirmRequest req) {
        paymentService.confirm(req);
        return ResponseEntity.ok("보증금 결제가 확인되었습니다.");
        }
    }
