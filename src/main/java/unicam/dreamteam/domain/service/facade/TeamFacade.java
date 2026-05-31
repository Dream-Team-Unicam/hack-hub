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
import unicam.dreamteam.presentation.dto.team.TeamDTO;
import unicam.dreamteam.presentation.mapper.TeamMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamFacade {
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final InvitoService invitoService;

    private final UtenteValidator utenteValidator;

    private final TeamMapper teamMapper;

    public List<TeamDTO> getAllTeams() {
        return this.teamService.getAll().stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    public TeamDTO getTeamByUsername(String username) {
        Utente currentUser = this.utenteService.getByUsername(username);
        this.utenteValidator.validaInTeam(currentUser);
        return this.teamMapper.toDTO(currentUser.getTeam());
    }

    public TeamDTO creaTeam(CreateTeamRequest request, String username) {
        Utente currentUser = this.utenteService.getByUsername(username);
        Team team =  this.teamService.creaTeam(
                request.name(),
                request.descrizione(),
                currentUser
        );

        return this.teamMapper.toDTO(team);
    }

    public TeamDTO esciDalTeam(String username) {
        Utente currentUser = utenteService.getByUsername(username);
        utenteValidator.validaInTeam(currentUser);

        Team team = currentUser.getTeam();
        team.rimuoviMembro(currentUser);
        utenteService.save(currentUser);  // ← salva utente con team = null

        if (team.getMembri().isEmpty()) {
            team.getSottomissioni().clear();
            teamService.save(team);
            teamService.delete(team);
            return new TeamDTO();
        }

        return teamMapper.toDTO(teamService.save(team));
    }
}
