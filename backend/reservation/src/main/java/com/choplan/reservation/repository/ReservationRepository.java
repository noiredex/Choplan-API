package com.choplan.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.choplan.reservation.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
