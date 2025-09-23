package choplan.db.application.properties.choplan.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable + false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password; // 비밀번호 Hash 처리

    @Column(length = 20, unique = true)
    private String phone;

    @Column(length = 50)
    private String nickname; // CUSTOMER만 필수

    @Column(nullable = true)
    private String businessRegistrationDoc; // OWNER만 필수

    @Column(length = 10)
    private UserRole role; // CUSTOMER, OWNER, ADMIN

    @Column(length = 10)
    private String status; // ACTIVE, SUSPENDED / DELETED

    @Builder.Default
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
