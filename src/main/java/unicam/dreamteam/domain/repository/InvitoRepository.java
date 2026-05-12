package unicam.dreamteam.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.dreamteam.domain.model.Invito;

public interface InvitoRepository extends JpaRepository<Invito, Long> {
}
