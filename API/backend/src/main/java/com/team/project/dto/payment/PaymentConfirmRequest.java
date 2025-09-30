package com.team.project.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentConfirmRequest {
    private String reservationId;
    private String impUid;
    private String merchantUid;
}
