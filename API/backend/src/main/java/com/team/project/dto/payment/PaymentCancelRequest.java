package com.team.project.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCancelRequest {
    private Integer refundAmount;
    private String reason;
}
