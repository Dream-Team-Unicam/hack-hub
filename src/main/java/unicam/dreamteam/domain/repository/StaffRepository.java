package unicam.dreamteam.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.users.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
