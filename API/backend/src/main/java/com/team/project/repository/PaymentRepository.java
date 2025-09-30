package com.team.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.project.entity.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByMerchantUid(String merchantUid);
    Optional<Payment> findByImpUid(String impUid);
}
