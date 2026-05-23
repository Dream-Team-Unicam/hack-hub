package unicam.dreamteam.infrastructure.repository;

import unicam.dreamteam.domain.model.Hackathon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    @Query("SELECT DISTINCT h FROM Hackathon h LEFT JOIN FETCH h.mentori LEFT JOIN FETCH h.teamIscritti LEFT JOIN FETCH h.organizzatore LEFT JOIN FETCH h.giudice")
    List<Hackathon> findAllWithDetails();

    List<Hackathon> findAllByGiudiceId(Long id);
}
