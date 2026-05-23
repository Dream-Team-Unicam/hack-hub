package unicam.dreamteam.presentation.api.controllers.team;

import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;
import unicam.dreamteam.presentation.mapper.TeamMapper;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/team")
@PreAuthorize("hasRole('UTENTE')")
@AllArgsConstructor
public class TeamUtenteLoggatoController {
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final TeamMapper teamMapper;
    private final UtenteValidator validator;

    @GetMapping
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<TeamResponse> getMyTeam(Authentication authentication) {
        Utente currentUser = this.utenteService.getByUsername(authentication.getName());

        this.validator.validInTeam(currentUser);

        return ResponseEntity.ok(this.teamMapper.toResponse(currentUser.getTeam()));
    }
}
