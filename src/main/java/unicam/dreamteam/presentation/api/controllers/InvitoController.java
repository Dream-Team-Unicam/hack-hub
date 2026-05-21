package unicam.dreamteam.presentation.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unicam.dreamteam.domain.exception.team.UserNotInATeamException;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.state.invito.StatoInvito;
import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.model.users.ruolo.RuoloUtente;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.security.Autenticabile;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.presentation.dto.team.requests.InvitaMembroRequest;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.mapper.InvitoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/inviti")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class InvitoController {
    private InvitoService invitoService;
    private SecurityService securityService;

    private StaffService staffService;
    private UtenteService utenteService;


    private InvitoMapper invitoMapper;

    /**
     * Restituisce gli inviti in stato pendente. Se l'utente ha il ruolo {@code UTENTE} vengono
     * restituiti solo i propri inviti, se ha il ruolo {@code ADMIN} vengono restituiti tutti.
     *
     * @param authentication le informazioni sull'autenticazione dell'utente corrente
     * @return la lista degli inviti in stato pendente, vedi {@link InvitoResponse}
     */
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'UTENTE')")
    public ResponseEntity<List<InvitoResponse>> getAll(Authentication authentication) {
        Autenticabile account = securityService.getAccountByUsername(authentication.getName());


        if (account.getRuolo().equals(RuoloUtente.UTENTE)) {
            Utente utente = (Utente) account;

            return ResponseEntity.ok(
                    this.invitoService.getInvitiByUtenteAndStato(utente, new StatoInvitoPendente()).stream()
                            .map(this.invitoMapper::toResponse)
                            .toList());
        }

        return ResponseEntity.ok(
                this.invitoService.getInvitiByStato(new StatoInvitoPendente()).stream()
                        .map(this.invitoMapper::toResponse)
                        .toList());
    }


    /**
     * Invita un utente a unirsi al proprio team.
     *
     * @param request        le informazioni necessarie per creare l'invito, vedi {@link InvitaMembroRequest}
     * @param authentication le informazioni sull'autenticazione dell'utente corrente
     * @return le informazioni dell'invito creato, vedi {@link InvitoResponse}
     */
    // TODO: Gestione Dimensione Massima Team per hackathon
    @PostMapping()
    @PreAuthorize("hasRole('UTENTE')")
    public ResponseEntity<InvitoResponse> invitaNelTeam(@Valid @RequestBody InvitaMembroRequest request, Authentication authentication) {
        Utente currentUser = this.utenteService.getByUsername(authentication.getName());

        Invito invito = this.invitoService.invita(
                getOrThrowTeamUtente(currentUser),
                request.usernameUtenteInvitato()
        );
        return ResponseEntity.ok(this.invitoMapper.toResponse(invito));
    }

    /**
     * Verifica se l'utente appartiene a un team.
     *
     * @param utente l'utente da verificare
     * @return il team di cui l'utente fa parte
     * @throws UserNotInATeamException se l'utente non appartiene ad alcun team
     */
    private Team getOrThrowTeamUtente(Utente utente) {
        if(!utente.hasTeam())
            throw new UserNotInATeamException(utente.getUsername());
        return utente.getTeam();
    }
}
