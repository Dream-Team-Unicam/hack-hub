package unicam.dreamteam.domain.service.team;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.exception.team.TeamNameAlreadyExistsException;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.domain.service.security.Autenticabile;
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

    public List<Team> getTeamsForAccount(Autenticabile account) {
        if (account.getRuolo().equals(RuoloStaff.ADMIN)) return getAll();
        if (account instanceof Utente utente) {
            System.out.println(utente.hasTeam());
            if (utente.hasTeam()) return List.of(utente.getTeam());
            throw new EntityNotFoundException(
                    String.format(
                            "Utente.username=%s non appartiene ad un team.",
                            account.getUsername()
                    )
            );
        }

        throw new AccessDeniedException("Ruolo non autorizzato.");
    }

    public Team creaTeam(String nome, String descrizione, Utente owner) {
        if (owner.hasTeam()) throw new IllegalStateException("Sei già membro di un team");
        if (teamRepository.existsByNome(nome))
            throw new TeamNameAlreadyExistsException(String.format("Team.nome=%s", nome));

        Team newTeam = new Team(nome, descrizione);
        newTeam = teamRepository.save(newTeam);
        newTeam.aggiungiMembro(owner);
        utenteRepository.save(owner);
        return teamRepository.save(newTeam);
    }
}
