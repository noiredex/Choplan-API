package com.choplan.mypage.dto;

public record ReservationDto (
    Long id,
    String storeName,
    String date,
    int people,
    String status
) {}
