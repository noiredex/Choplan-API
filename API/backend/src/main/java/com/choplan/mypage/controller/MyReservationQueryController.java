package com.choplan.mypage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.mypage.dto.ReservationDto;
import com.choplan.mypage.service.MyReservationQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyReservationQueryController {

    private final MyReservationQueryService myReservationsQueryService;

    @GetMapping("/reservations")
    public List<ReservationDto> myReservations() {
        return myReservationsQueryService.myReservations();
    }
}
