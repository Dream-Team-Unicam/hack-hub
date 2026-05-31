package unicam.dreamteam.presentation.api.controllers.team;

import unicam.dreamteam.domain.service.facade.TeamFacade;
import unicam.dreamteam.presentation.dto.team.requests.CreateTeamRequest;
import unicam.dreamteam.presentation.dto.team.TeamDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class TeamController {
    private final TeamFacade teamFacade;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamDTO>> index() {
        return ResponseEntity.ok(
                this.teamFacade.getAllTeams()
        );
    }

    @PostMapping()
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<TeamDTO> creaTeam(@Valid @RequestBody CreateTeamRequest request, Authentication authentication) {
        return ResponseEntity.ok(
                this.teamFacade.creaTeam(
                        request,
                        authentication.getName()
                )
        );
    }
}