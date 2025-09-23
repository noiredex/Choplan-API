package choplan.db.application.properties.choplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import choplan.db.application.properties.choplan.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    
}
