package unicam.dreamteam.domain.service.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.entity.Segnalazione;
import unicam.dreamteam.domain.model.entity.Team;
import unicam.dreamteam.domain.model.entity.users.Staff;
import unicam.dreamteam.domain.repository.SegnalazioneRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SegnalazioneService {
    private final SegnalazioneRepository repository;

    public Segnalazione segnala(String descrizione, Staff mentore, Team team) {
        return repository.save(new Segnalazione(descrizione, mentore, team));
    }

    public List<Segnalazione> getAll() {
        return repository.findAll();
    }

    public List<Segnalazione> getByTeam(Long teamId) {
        return repository.findByTeamSegnalatoId(teamId);
    }
}
