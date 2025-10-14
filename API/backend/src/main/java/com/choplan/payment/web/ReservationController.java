package com.choplan.payment.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.payment.dto.ReservationDraftRequest;
import com.choplan.payment.dto.ReservationResponse;
import com.choplan.payment.service.ReservationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin(origins = { "http://localhost:5173" }, allowCredentials = "true")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/draft")
    public ResponseEntity<ReservationResponse> createDraft(@Valid @RequestBody ReservationDraftRequest req,
            HttpSession session) {
        Long id = reservationService.createDraft(req);
        session.setAttribute("reservationId", id);
        return ResponseEntity.ok(new ReservationResponse(id));
    }
}
