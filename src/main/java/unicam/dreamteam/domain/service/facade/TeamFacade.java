package unicam.dreamteam.domain.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;
import unicam.dreamteam.presentation.dto.team.requests.CreateTeamRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamFacade {
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final InvitoService invitoService;

    private final UtenteValidator validator;

    public List<Team> getAllTeams() {
        return this.teamService.getAll();
    }

    public Team getTeamByUsername(String username) {
        Utente currentUser = this.utenteService.getByUsername(username);
        this.validator.validaInTeam(currentUser);
        return currentUser.getTeam();
    }

    public Team creaTeam(CreateTeamRequest request, String username) {
        Utente currentUser = this.utenteService.getByUsername(username);
        return this.teamService.creaTeam(
                request.name(),
                request.descrizione(),
                currentUser
        );
    }
}
