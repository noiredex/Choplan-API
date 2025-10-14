package com.choplan.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choplan.payment.domain.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
