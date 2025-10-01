package com.choplan.reservation.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReservationDraftRequest {
    @NotNull private Long storeId;
    @NotNull private LocalDateTime reservationDatetime;
    @Min(1) private int partySize;
    @NotBlank private String contactPhone;
    private String specialRequests;
}
