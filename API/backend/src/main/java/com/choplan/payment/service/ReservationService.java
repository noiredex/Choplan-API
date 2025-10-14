package com.choplan.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choplan.payment.domain.Reservation;
import com.choplan.payment.dto.ReservationDraftRequest;
import com.choplan.payment.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public Long createDraft(ReservationDraftRequest req) {
        Reservation r = Reservation.builder()
                .storeId(req.getStoreId())
                .reservationDatetime(req.getReservationDatetime())
                .partySize(req.getPartySize())
                .contactPhone(req.getContactPhone())
                .specialRequests(req.getSpecialRequests())
                .status("PENDING")
                .build();
        reservationRepository.save(r);
        return r.getId();
    }

    @Transactional
    public void markPaid(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        r.setStatus("PAID");
    }
}
