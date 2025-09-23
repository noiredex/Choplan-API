package choplan.db.application.properties.choplan.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 150)
    private String email; // Email

    @Column(nullable = false, length = 255)
    private String passwordHash; // 비밀번호 Hash 처리

    @Column(length = 20, unique = true)
    private String phone; // CUSTOMER, OWNER 공통 Phone Number

    @Column(length = 50)
    private String nickname; // CUSTOMER만 필수

    @Column(nullable = true)
    private String businessRegistrationDoc; // OWNER만 필수

    @Column(length = 20)
    private String storePhone ; // 매장 전화번호 OWNER만 필수

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserRole role; // CUSTOMER, OWNER, ADMIN

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserStatus status = UserStatus.ACTIVE; // ACTIVE를 기본값으로 설정

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime updatedAt;

    public enum UserStatus {
        ACTIVE,
        SUSPENDED,
        DELETED
    }
}
