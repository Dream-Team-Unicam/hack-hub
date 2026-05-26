package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.facade.HackathonFacade;
import unicam.dreamteam.domain.service.hackathon.HackathonService;
import unicam.dreamteam.domain.service.accounts.StaffService;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;
import unicam.dreamteam.presentation.dto.hackathon.sottomissione.SottomissioneDTO;
import unicam.dreamteam.presentation.mapper.HackathonMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import lombok.AllArgsConstructor;
import unicam.dreamteam.presentation.mapper.SottomissioneMapper;

@RestController
@RequestMapping("/api/hackathons")
@AllArgsConstructor
public class HackathonController {
    private HackathonService hackathonService;
    private StaffService staffService;
    private UtenteService utenteService;

    private HackathonFacade hackathonFacade;

    private SottomissioneMapper sottomissioneMapper;
    private HackathonMapper hackathonMapper;

    @GetMapping
    public ResponseEntity<List<HackathonDTO>> index() {
        return ResponseEntity.ok(
                this.hackathonService.getAllHackathons().stream()
                        .map(this.hackathonMapper::toResponse)
                        .toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> creaHackathon(@RequestBody HackathonDTO request, Authentication authentication) {
        Staff currentUser = this.staffService.getByUsername(authentication.getName());
        Staff giudice = this.staffService.getById(request.getGiudiceId());

        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonService.save(request, currentUser, giudice)
                )
        );
    }

    @PostMapping("/{id}/add/mentore")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> aggiungiMentore(@PathVariable Long id, @RequestBody Long mentoreId) {
        Staff mentore = this.staffService.getById(mentoreId);
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonService.aggiungi(id, mentore)
                )
        );
    }

    @PostMapping("/{id}/remove/mentore")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> rimuoviMentore(@PathVariable Long id, @RequestBody Long mentoreId) {
        Staff mentore = this.staffService.getById(mentoreId);
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonService.aggiungi(id, mentore)
                )
        );
    }

    @PostMapping("/{id}/apri-iscrizioni")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> apriIscrizioni(@PathVariable Long id) {
        return ResponseEntity.ok(
                hackathonMapper.toResponse(
                        hackathonFacade.apriIscrizioni(id)
                )
        );
    }

    @PostMapping("/{id}/iscrivi")
    @PreAuthorize("hasAnyRole('UTENTE', 'TEAM_LEADER')")
    public ResponseEntity<HackathonDTO> iscriviTeam(
            @PathVariable Long id,
            Authentication authentication) {
        Utente utente = utenteService.getByUsername(authentication.getName());
        Hackathon hackathon = hackathonFacade.iscriviTeam(id, utente);
        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(hackathon)
        );
    }

    @PostMapping("/{id}/avvia")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> avviaHackathon(@PathVariable Long id) {
        return ResponseEntity.ok(
                hackathonMapper.toResponse(
                        hackathonFacade.avviaHackathon(id)
                )
        );
    }

    @PostMapping("/{id}/sottomissioni")
    @PreAuthorize("hasAnyRole('UTENTE', 'TEAM_MEMBER', 'TEAM_LEADER')")
    public ResponseEntity<SottomissioneDTO> inviaSottomissione(
            @PathVariable Long id,
            @RequestBody String contenuto,
            Authentication authentication) {
        Utente utente = utenteService.getByUsername(authentication.getName());
        return ResponseEntity.ok(
                this.sottomissioneMapper.toResponse(
                        this.hackathonFacade.inviaSottomissione(id, utente, contenuto)
                )
        );
    }
}
