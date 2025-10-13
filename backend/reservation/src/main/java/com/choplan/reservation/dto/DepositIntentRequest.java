package com.choplan.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DepositIntentRequest {
    @NotNull
    private Long reservationId;
}