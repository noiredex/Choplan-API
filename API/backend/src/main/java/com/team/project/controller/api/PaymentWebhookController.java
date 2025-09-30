package com.team.project.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.project.dto.payment.WebhookPayload;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments/webhooks")
@RequiredArgsConstructor
public class PaymentWebhookController {

    @PostMapping
    public ResponseEntity<Void> webhook(
            @RequestBody WebhookPayload payload,
            @RequestHeader(value = "X-PORTONE-SIGNATURE", required = false) String sig) {
        return ResponseEntity.ok().build();
    }
}
