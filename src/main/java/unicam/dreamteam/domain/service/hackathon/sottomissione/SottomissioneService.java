package unicam.dreamteam.domain.service.hackathon.sottomissione;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.repository.SottomissioneRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SottomissioneService {

    private SottomissioneRepository sottomissioneRepository;

    public Sottomissione save(Sottomissione sottomissione) {
        return this.sottomissioneRepository.save(sottomissione);
    }

    public Sottomissione getByHackathonAndTeam(Hackathon hackathon, Team team) {
        Optional<Sottomissione> sottomissione = sottomissioneRepository.findByHackathonIdAndTeamId(
                hackathon.getId(),
                team.getId()
        );
        if (sottomissione.isEmpty())
            throw new EntityNotFoundException("Nessuna sottomissione trovata per il tuo team.");

        return sottomissione.get();
    }
}
