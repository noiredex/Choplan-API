package com.choplan.mypage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choplan.mypage.dto.ReservationDto;
import com.choplan.mypage.service.MyReservationQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyReservationQueryServiceImpl implements MyReservationQueryService {

    @Override
    public List<ReservationDto> myReservations() {
        return List.of(
                new ReservationDto(1L, "매장에이", "2025-10-29", 4, "CONFIRMED"));
    }
}
