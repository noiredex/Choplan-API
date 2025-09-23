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

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 20, unique = true)
    private String phone;

    @Column(length = 50)
    private String nickname;

    @Column(length = 10)
    private String role;

    @Builder.Default
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
