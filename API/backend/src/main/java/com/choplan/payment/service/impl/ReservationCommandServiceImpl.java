package com.choplan.payment.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choplan.payment.domain.Reservation;
import com.choplan.payment.dto.ReservationDraftRequest;
import com.choplan.payment.repository.ReservationRepository;
import com.choplan.payment.service.ReservationCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;

    @Override
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

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
            r.setStatus("CANCELLED");
    }

    @Override
    @Transactional
    public void cancelDeposit(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        r.setStatus("DEPOSIT_CANCELLED");
    }

    @Override
    @Transactional
    public void markPaid(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        r.setStatus("PAID");
    }
}
