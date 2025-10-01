package com.choplan.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PaymentConfirmRequest {
    @NotNull private Long reservationId;
    @NotBlank private String impUid;
    @NotBlank private String merchantUid;
}
