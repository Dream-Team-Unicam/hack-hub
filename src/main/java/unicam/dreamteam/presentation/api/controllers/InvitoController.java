package unicam.dreamteam.presentation.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.model.users.ruolo.RuoloUtente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.facade.InvitoFacade;
import unicam.dreamteam.domain.service.security.Autenticabile;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;
import unicam.dreamteam.presentation.dto.team.requests.InvitaMembroRequest;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.mapper.InvitoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/inviti")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class InvitoController {
    private InvitoFacade invitoFacade;
    private InvitoMapper invitoMapper;

    /**
     * Restituisce gli inviti in stato pendente, per staff con ruolo {@code ADMIN}. Se l'utente ha il ruolo {@code UTENTE} vengono
     * restituiti solo i propri inviti, se ha il ruolo {@code ADMIN} vengono restituiti tutti.
     *
     * @param authentication le informazioni sull'autenticazione dell'utente corrente
     * @return la lista degli inviti in stato pendente, vedi {@link InvitoResponse}
     */
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'UTENTE')")
    public ResponseEntity<List<InvitoResponse>> index(Authentication authentication) {
        return ResponseEntity.ok(
                this.invitoFacade.getAllByPendentiByAccountUsername(authentication.getName()).stream()
                        .map(this.invitoMapper::toResponse)
                        .toList()
        );
    }

    /**
     * Invita un utente a unirsi al proprio team.
     *
     * @param request        le informazioni necessarie per creare l'invito, vedi {@link InvitaMembroRequest}
     * @param authentication le informazioni sull'autenticazione dell'utente corrente
     * @return le informazioni dell'invito creato, vedi {@link InvitoResponse}
     */
    // TODO: Gestione Dimensione Massima Team per hackathon
    // TODO: Utente tecnicamente non puo' invitare se stesso nel suo team perchè è già in un team. (Gestirlo?)
    @PostMapping()
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<InvitoResponse> invita(@Valid @RequestBody InvitaMembroRequest request, Authentication authentication) {
        return ResponseEntity.ok(this.invitoMapper.toResponse(
                this.invitoFacade.invita(
                        authentication.getName(),
                        request.usernameUtenteInvitato()
                )
        ));
    }

    @PostMapping("/accetta")
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<InvitoResponse> accetta(@Valid @RequestBody Long idTeam, Authentication authentication) {
        return ResponseEntity.ok(this.invitoMapper.toResponse(
                this.invitoFacade.accetta(
                        idTeam,
                        authentication.getName()
                )
        ));
    }

    @PostMapping("/rifiuta")
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<InvitoResponse> rifiuta(@Valid @RequestBody Long idTeam, Authentication authentication) {
        return ResponseEntity.ok(this.invitoMapper.toResponse(
                this.invitoFacade.rifiuta(
                        idTeam,
                        authentication.getName()
                )
        ));
    }
}
