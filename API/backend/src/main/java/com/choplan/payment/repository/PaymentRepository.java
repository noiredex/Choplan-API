package com.choplan.payment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.choplan.payment.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByImpUid(String impUid);
    Optional<Payment> findByMerchantUid(String merchantUid);
    Optional<Payment> findTopByReservationIdAndStatusOrderByIdDesc(Long reservationId,String status);
}
