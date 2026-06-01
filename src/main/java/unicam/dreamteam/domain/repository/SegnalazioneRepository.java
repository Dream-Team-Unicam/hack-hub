package unicam.dreamteam.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.dreamteam.domain.model.entity.Segnalazione;

import java.util.List;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
    List<Segnalazione> findByTeamSegnalatoId(Long teamId);
    List<Segnalazione> findByMentoreId(Long mentoreId);
}
