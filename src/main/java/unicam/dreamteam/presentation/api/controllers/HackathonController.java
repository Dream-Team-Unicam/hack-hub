package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.service.facade.HackathonFacade;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;
import unicam.dreamteam.presentation.dto.hackathon.sottomissione.SottomissioneDTO;
import unicam.dreamteam.presentation.mapper.HackathonMapper;
import unicam.dreamteam.presentation.mapper.SottomissioneMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hackathons")
@AllArgsConstructor
public class HackathonController {
    private HackathonFacade hackathonFacade;

    private SottomissioneMapper sottomissioneMapper;
    private HackathonMapper hackathonMapper;

    @GetMapping
    public ResponseEntity<List<HackathonDTO>> index() {
        return ResponseEntity.ok(
                this.hackathonFacade.listaHackathon().stream()
                        .map(this.hackathonMapper::toResponse)
                        .toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> creaHackathon(@RequestBody HackathonDTO request, Authentication authentication) {
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonFacade.creaHackathon(
                                request,
                                authentication
                        )
                )
        );
    }

    @PostMapping("/{hackathonId}/add/mentore/{mentoreId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> aggiungiMentore(@PathVariable Long hackathonId, @PathVariable Long mentoreId) {
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonFacade.aggiungiMentore(
                                hackathonId,
                                mentoreId
                        )
                )
        );
    }

    @DeleteMapping("/{hackathonId}/mentori/{mentoreId}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> rimuoviMentore(@PathVariable Long hackathonId, @PathVariable Long mentoreId) {
        return ResponseEntity.ok(
                hackathonMapper.toResponse(
                        hackathonFacade.rimuoviMentore(
                                hackathonId,
                                mentoreId
                        )
                )
        );
    }

    @PostMapping("/{hackathonId}/apri-iscrizioni")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> apriIscrizioni(@PathVariable Long hackathonId) {
        return ResponseEntity.ok(
                hackathonMapper.toResponse(
                        hackathonFacade.apriIscrizioni(
                                hackathonId
                        )
                )
        );
    }

    @PostMapping("/{hackathonId}/iscrivi")
    @PreAuthorize("hasAnyRole('UTENTE', 'TEAM_LEADER')")
    public ResponseEntity<HackathonDTO> iscriviTeam(@PathVariable Long hackathonId, Authentication authentication) {
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonFacade.iscriviTeam(
                                hackathonId,
                                authentication.getName() // Username dell'utente loggato.
                        )
                )
        );
    }

    @PostMapping("/{hackathonId}/avvia")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> avviaHackathon(@PathVariable Long hackathonId) {
        return ResponseEntity.ok(
                hackathonMapper.toResponse(
                        hackathonFacade.avviaHackathon(hackathonId)
                )
        );
    }

    @PostMapping("/{hackathonId}/sottomissioni/send")
    @PreAuthorize("hasAnyRole('UTENTE', 'TEAM_MEMBER', 'TEAM_LEADER')")
    public ResponseEntity<SottomissioneDTO> inviaSottomissione(@PathVariable Long hackathonId, @RequestBody String contenuto, Authentication authentication) {
        return ResponseEntity.ok(
                this.sottomissioneMapper.toResponse(
                        this.hackathonFacade.inviaSottomissione(
                                hackathonId,
                                authentication.getName(), // Username utente loggato
                                contenuto
                        )
                )
        );
    }

    @PostMapping("/{hackathonId}/sottomissioni/update")
    @PreAuthorize("hasAnyRole('UTENTE', 'TEAM_MEMBER', 'TEAM_LEADER')")
    public ResponseEntity<SottomissioneDTO> aggiornaSottomissione(@PathVariable Long hackathonId, @RequestBody String nuovoContenuto, Authentication authentication) {
        return ResponseEntity.ok(
                sottomissioneMapper.toResponse(
                        hackathonFacade.aggiornaSottomissione(
                                hackathonId,
                                authentication.getName(), // Username utente loggato
                                nuovoContenuto
                        )
                )
        );
    }
}
