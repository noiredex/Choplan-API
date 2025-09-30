package com.team.project.entity.payment;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "payments", indexes = { 
    @Index(name = "idx_pay_merchant_uid", columnList = "merchantUid", unique = true)})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) 
    private String reservationId;
    @Column(nullable = false) 
    private String storeId;
    @Column(nullable = false, unique = true) 
    private String merchantUid;
    @Column(unique = true) 
    private String impUid;
    @Column(nullable = false) 
    private Integer amount;
    @Enumerated(EnumType.STRING) @Column(nullable = false) 
    private PaymentStatus status;
    private Instant paidAt;
    private Instant cancelledAt;
    @Lob @Column(columnDefinition = "LONGTEXT") private String raw;
    
}
