package choplan.db.application.properties.choplan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entitiy
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

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
