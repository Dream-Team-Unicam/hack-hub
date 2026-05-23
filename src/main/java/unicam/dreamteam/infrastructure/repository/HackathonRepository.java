package unicam.dreamteam.infrastructure.repository;

import org.springframework.data.repository.query.Param;
import unicam.dreamteam.domain.model.Hackathon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    @Query("SELECT DISTINCT h FROM Hackathon h " +
            "LEFT JOIN FETCH h.mentori " +
            "LEFT JOIN FETCH h.teamIscritti " +
            "LEFT JOIN FETCH h.organizzatore " +
            "LEFT JOIN FETCH h.giudice")
    List<Hackathon> findAllWithDetails();

    @Query("SELECT DISTINCT h FROM Hackathon h " +
            "LEFT JOIN FETCH h.mentori " +
            "LEFT JOIN FETCH h.teamIscritti " +
            "LEFT JOIN FETCH h.organizzatore " +
            "LEFT JOIN FETCH h.giudice " +
            "WHERE h.id = :id")
    Optional<Hackathon> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT DISTINCT h FROM Hackathon h " +
            "LEFT JOIN FETCH h.mentori " +
            "LEFT JOIN FETCH h.teamIscritti " +
            "LEFT JOIN FETCH h.organizzatore " +
            "LEFT JOIN FETCH h.giudice " +
            "WHERE h.giudice.id = :giudiceId")
    List<Hackathon> findAllByGiudiceIdWithDetails(@Param("giudiceId") Long giudiceId);

}
