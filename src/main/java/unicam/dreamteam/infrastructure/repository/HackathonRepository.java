package unicam.dreamteam.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
}
