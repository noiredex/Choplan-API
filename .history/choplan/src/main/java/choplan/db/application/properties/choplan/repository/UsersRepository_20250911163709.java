package choplan.db.application.properties.choplan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import choplan.db.application.properties.choplan.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}