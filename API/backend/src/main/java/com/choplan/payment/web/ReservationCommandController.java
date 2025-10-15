package com.choplan.payment.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.payment.dto.ReservationDraftRequest;
import com.choplan.payment.dto.ReservationResponse;
import com.choplan.payment.service.impl.ReservationCommandServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationCommandController {
    private final ReservationCommandServiceImpl reservationService;

    @PostMapping("/draft")
    public ResponseEntity<ReservationResponse> createDraft(@Valid @RequestBody ReservationDraftRequest req,
            HttpSession session) {
        Long id = reservationService.createDraft(req);
        session.setAttribute("reservationId", id);
        return ResponseEntity.ok(new ReservationResponse(id));
    }

    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reservationId}/deposit/cancel")
    public ResponseEntity<Void> cancelDeposit(@PathVariable Long reservationId) {
        reservationService.cancelDeposit(reservationId);
        return ResponseEntity.ok().build();
    }
}
