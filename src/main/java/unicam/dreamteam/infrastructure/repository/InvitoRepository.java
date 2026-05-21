package unicam.dreamteam.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.state.invito.StatoInvito;

import java.util.List;

public interface InvitoRepository extends JpaRepository<Invito, Long> {
    List<Invito> findByTeamId(Long idTeam);
    List<Invito> findByStato(StatoInvito stato);
    List<Invito> findByUtenteIdAndStato(Long utenteId, StatoInvito stato);

    boolean existsByUtenteIdAndTeamId(Long idUtente, Long idTeam);
}
