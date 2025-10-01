package com.choplan.reservation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.choplan.reservation.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByMerchantUid(String merchantUid);
}
