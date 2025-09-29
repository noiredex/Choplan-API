package choplan.db.application.properties.choplan.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
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
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 50, nullable = false)
    private String realName;

    @Column(length = 20, unique = true)
    private String phone;

    @Column(length = 10)
    private String nickname; // CUSTOMER만 필수

    @Column(nullable = true)
    private String businessRegistrationDoc; // OWNER만 (파일 저장 URL)

    @Column(length = 100)
    private String storeName;

    @Column(length = 20)
    private String storePhone;

    @Embedded
    private StoreAddress storeAddress;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserRole role;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private OwnerStatus ownerStatus = OwnerStatus.PENDING; // 관리자가 승인해야 이용 가능

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public String getPasswordHash() {
        return this.passwordHash;
    }

public void srtPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}
    public enum UserStatus {
        ACTIVE, SUSPENDED, DELETED
    }
}
