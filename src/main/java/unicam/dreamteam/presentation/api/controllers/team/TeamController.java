package unicam.dreamteam.presentation.api.controllers.team;

import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.presentation.dto.team.requests.CreateTeamRequest;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;
import unicam.dreamteam.presentation.mapper.InvitoMapper;
import unicam.dreamteam.presentation.mapper.TeamMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final SecurityService securityService;
    private final UtenteService utenteService;
    private final InvitoService invitoService;

    // Mappers
    private final TeamMapper teamMapper;
    private final InvitoMapper invitoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamResponse>> getAllTeamsByAccount(Authentication authentication) {
        return ResponseEntity.ok(
                teamService.getAll().stream()
                        .map(teamMapper::toResponse)
                        .toList()
        );
    }

    @PostMapping()
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<TeamResponse> creaTeam(@Valid @RequestBody CreateTeamRequest request, Authentication authentication) {
        Utente currentUser = this.utenteService.getByUsername(authentication.getName());

        Team newTeam = this.teamService.creaTeam(
                request.name(),
                request.descrizione(),
                currentUser
        );

        return ResponseEntity.ok(this.teamMapper.toResponse(newTeam));
    }

    /**
     * Mostra gli inviti del proprio team. (Quelli inviati dal proprio team)
     * @param authentication
     * @return
     */
    @GetMapping("/inviti")
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<List<InvitoResponse>> invitiDelTeam(Authentication authentication) {
        Utente currentUser = this.utenteService
                .getByUsername(authentication.getName());

        if (!currentUser.hasTeam()) return ResponseEntity.ok(
                new ArrayList<>()
        );

        return ResponseEntity.ok(
                invitoService.getByTeamId(currentUser.getTeam().getId()).stream()
                        .map(invitoMapper::toResponse)
                        .toList()
        );
    }
}