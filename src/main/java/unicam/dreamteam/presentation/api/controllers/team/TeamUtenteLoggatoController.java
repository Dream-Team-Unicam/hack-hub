package unicam.dreamteam.presentation.api.controllers.team;

import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;
import unicam.dreamteam.presentation.mapper.InvitoMapper;
import unicam.dreamteam.presentation.mapper.TeamMapper;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/team")
@PreAuthorize("hasRole('UTENTE')")
@AllArgsConstructor
public class TeamUtenteLoggatoController {
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final InvitoService invitoService;

    private final TeamMapper teamMapper;
    private final InvitoMapper invitoMapper;
    private final UtenteValidator validator;

    /**
     * Mostra le informazioni del proprio team.
     */
    @GetMapping
    public ResponseEntity<TeamResponse> getMyTeam(Authentication authentication) {
        Utente currentUser = this.utenteService.getByUsername(authentication.getName());
        this.validator.validaInTeam(currentUser);

        return ResponseEntity.ok(this.teamMapper.toResponse(currentUser.getTeam()));
    }

    /**
     * Mostra gli inviti del proprio team. (Quelli inviati dal proprio team)
     */
    @GetMapping("/inviti")
    public ResponseEntity<List<InvitoResponse>> invitiDelTeam(Authentication authentication) {
        Utente currentUser = this.utenteService.getByUsername(authentication.getName());

        return ResponseEntity.ok(
                this.invitoService.getAllByUtentesTeam(currentUser).stream()
                        .map(this.invitoMapper::toResponse)
                        .toList()
        );
    }
}
