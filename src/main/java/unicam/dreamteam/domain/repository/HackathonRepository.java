package unicam.dreamteam.domain.repository;

import org.springframework.stereotype.Repository;
import unicam.dreamteam.domain.model.Hackathon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    List<Hackathon> findAllByGiudiceId(Long giudiceId);

}
