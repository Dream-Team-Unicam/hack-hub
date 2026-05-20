package unicam.dreamteam.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.Invito;

import java.util.List;

public interface InvitoRepository extends JpaRepository<Invito, Long> {
    List<Invito> findByTeamId(Long idTeam);
    boolean existsByUtenteIdAndTeamId(Long idUtente, Long idTeam);
}
