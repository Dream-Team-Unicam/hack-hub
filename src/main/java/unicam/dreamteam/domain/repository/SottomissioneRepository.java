package unicam.dreamteam.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;

import java.util.Optional;

@Repository
public interface SottomissioneRepository extends JpaRepository<Sottomissione, Long> {
    Optional<Sottomissione> findByHackathonIdAndTeamId(Long hackathonId, Long teamId);
}
