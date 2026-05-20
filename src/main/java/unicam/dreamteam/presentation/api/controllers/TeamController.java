package unicam.dreamteam.presentation.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unicam.dreamteam.domain.exception.team.UserNotInATeamException;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.presentation.dto.team.requests.CreateTeamRequest;
import unicam.dreamteam.presentation.dto.team.requests.InvitaMembroRequest;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;
import unicam.dreamteam.presentation.mapper.InvitoMapper;
import unicam.dreamteam.presentation.mapper.TeamMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;
    private UtenteService utenteService;
    private InvitoService invitoService;

    private TeamMapper teamMapper;
    private InvitoMapper invitoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        return ResponseEntity.ok(
                teamService.getAll().stream()
                        .map(this.teamMapper::toResponse)
                        .toList()
        );
    }

    @PostMapping()
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<TeamResponse> creaTeam(@Valid @RequestBody CreateTeamRequest request, Authentication authentication) {
        Utente currentUser = this.utenteService
                .getByUsername(authentication.getName())
                .orElseThrow();

        Team newTeam = this.teamService.creaTeam(
                request.name(),
                request.descrizione(),
                currentUser
        );

        return ResponseEntity.ok(this.teamMapper.toResponse(newTeam));
    }
    // TODO: L'utente chiamante invita nel proprio team un altro utente
    // TODO: Gestione Dimensione Massima Team per hackathon
    @PostMapping("/inviti")
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<InvitoResponse> invitaNelTeam(@Valid @RequestBody InvitaMembroRequest request, Authentication authentication) {
        Utente currentUser = this.utenteService
                .getByUsername(authentication.getName())
                .orElseThrow();

        if(!currentUser.hasTeam()) throw new UserNotInATeamException(currentUser.getUsername());
        Team currentUserTeam = currentUser.getTeam();

        Invito invito = this.invitoService.invita(
                currentUserTeam,
                request.idUtenteInvitato()
        );

        return ResponseEntity.ok(this.invitoMapper.toResponse(invito));
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
                .getByUsername(authentication.getName())
                .orElseThrow();

        if (!currentUser.hasTeam()) return ResponseEntity.ok(
                new ArrayList<>()
        );

        this.invitoService.getByTeamId(currentUser.getId());
        return ResponseEntity.ok(
                this.invitoService.getByTeamId(currentUser.getId()).stream()
                        .map(this.invitoMapper::toResponse)
                        .toList()
        );
    }
}