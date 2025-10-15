package com.choplan.payment.service;

import com.choplan.payment.dto.ReservationDraftRequest;

public interface ReservationCommandService {
    Long createDraft(ReservationDraftRequest req);
    void cancelReservation(Long reservationId);
    void cancelDeposit(Long reservationId);
    void markPaid(Long reservationId); //필요시 사용예정
}
