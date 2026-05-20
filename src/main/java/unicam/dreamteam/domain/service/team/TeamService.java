package unicam.dreamteam.domain.service.team;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.exception.team.TeamNameAlreadyExistsException;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.repository.TeamRepository;
import unicam.dreamteam.infrastructure.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {
    private TeamRepository teamRepository;
    private UtenteRepository utenteRepository;

    public List<Team> getAll() {
        return this.teamRepository.findAll();
    }

    public Team getById(Long idTeam) {
        Optional<Team> team = this.teamRepository.findById(idTeam);

        if (team.isEmpty()) throw new EntityNotFoundException(
                String.format(
                        "Team.id=%s",
                        idTeam
                )
        );

        return team.get();
    }

    public Team creaTeam(String nome, String descrizione, Utente owner) {
        // TODO: Custom Exception
        if (owner.hasTeam()) throw new IllegalStateException("Sei già membro di un team");
        if (teamRepository.existsByNome(nome)) throw new TeamNameAlreadyExistsException(nome);

        Team newTeam = new Team(nome, descrizione, owner);
        return this.teamRepository.save(newTeam);
    }
}
