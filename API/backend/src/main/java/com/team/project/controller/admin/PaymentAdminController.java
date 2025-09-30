package com.team.project.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.project.dto.payment.PaymentCancelRequest;
import com.team.project.entity.payment.Payment;
import com.team.project.service.api.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/payments")
@RequiredArgsConstructor
public class PaymentAdminController {
    private final PaymentService paymentService;

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<?> cancel(
        @PathVariable Long paymentId,
        @RequestBody PaymentCancelRequest req
    ) {
        Payment pay = paymentService.cancelPayment(paymentId, req);
        return ResponseEntity.ok().body(new ApiResponse<>(pay, "환불이 완료되었습니다."));
    }

    record ApiResponse<T>(T data, String message) {}
}
