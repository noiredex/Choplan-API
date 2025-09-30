package com.team.project.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentIntentRequest {
    private String reservationId;
    private String storeId;
    private Integer depositAmount;
    private String payMethod;
    private String orderName;
}
