package com.team.project.entity.payment;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "deposits")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Deposit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String reservationId;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "payment_id") private Payment payment;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private DepositStatus status;
    @Column(nullable = false) private Integer amount;
    @Column(nullable = false) private Integer refundedAmount;
    private Instant updatedAt;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
