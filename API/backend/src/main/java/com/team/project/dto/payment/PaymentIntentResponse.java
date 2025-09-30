package com.team.project.dto.payment;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentIntentResponse {
    private String merchantUid;
    private Integer amount;
    private String currency;
    private String orderName;
    private Map<String, Object> buyer;
    private Map<String, String> portone;
}
