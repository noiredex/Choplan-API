package com.team.project.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.project.dto.payment.PaymentConfirmRequest;
import com.team.project.dto.payment.PaymentIntentRequest;
import com.team.project.dto.payment.PaymentIntentResponse;
import com.team.project.entity.payment.Payment;
import com.team.project.service.api.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposits/intents")
    public ResponseEntity<PaymentIntentResponse> createDepositIntent(
            @RequestBody PaymentIntentRequest req,
            @RequestHeader(value = "X-USER-NAME", required = false) String name,
            @RequestHeader(value = "X-USER-EMAIL", required = false) String email,
            @RequestHeader(value = "X-USER-TEL", required = false) String tel) {
        var res = paymentService.createDepositIntent(req, name, email, tel);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deposits/confirm")
    public ResponseEntity<Payment> confirmDeposit(@RequestBody PaymentConfirmRequest req) {
        var pay = paymentService.confirmDeposit(req);
        return ResponseEntity.ok(pay);
    }
}
