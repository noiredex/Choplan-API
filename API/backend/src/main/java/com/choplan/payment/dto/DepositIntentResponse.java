package com.choplan.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class DepositIntentResponse {
    private String merchantUid;
    private int amount;
    private String name;
}
