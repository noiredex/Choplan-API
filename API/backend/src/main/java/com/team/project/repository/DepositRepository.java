package com.team.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.project.entity.payment.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Optional<Deposit> findByReservationId(String reservationId);
}
