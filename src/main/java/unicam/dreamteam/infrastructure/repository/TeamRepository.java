package unicam.dreamteam.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByNome(String nome);

    Long id(Long id);
}
