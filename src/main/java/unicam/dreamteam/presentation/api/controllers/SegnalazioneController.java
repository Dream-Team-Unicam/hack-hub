package unicam.dreamteam.presentation.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unicam.dreamteam.domain.service.facade.SegnalazioneFacade;
import unicam.dreamteam.presentation.dto.team.richiesteSupporto.SegnalazioneDTO;

import java.util.List;

@RestController
@RequestMapping("/api/segnalazioni")
@AllArgsConstructor
public class SegnalazioneController {
    private final SegnalazioneFacade segnalazioneFacade;

    @PostMapping("/hackathon/{hackathonId}/team/{teamId}")
    @PreAuthorize("hasRole('MENTORE')")
    public ResponseEntity<SegnalazioneDTO> segnalaTeam(
            @PathVariable Long hackathonId,
            @PathVariable Long teamId,
            @RequestBody String descrizione,
            Authentication authentication) {
        return ResponseEntity.ok(
                segnalazioneFacade.segnalaTeam(hackathonId, teamId, descrizione, authentication.getName())
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<List<SegnalazioneDTO>> getAll() {
        return ResponseEntity.ok(segnalazioneFacade.getAll());
    }

    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<List<SegnalazioneDTO>> getByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(segnalazioneFacade.getByTeam(teamId));
    }
}
