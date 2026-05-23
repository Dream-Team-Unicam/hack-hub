package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.service.HackathonService;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.domain.validator.RuoloValidator;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;
import unicam.dreamteam.presentation.mapper.HackathonMapper;

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
    private HackathonService hackathonService;
    private SecurityService securityService;
    private StaffService staffService;
    private UtenteService utenteService;

    private HackathonMapper hackathonMapper;

    @GetMapping
    public ResponseEntity<List<HackathonDTO>> getAllHackathons() {
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

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public ResponseEntity<HackathonDTO> aggiungiMentore(@PathVariable Long id, @RequestBody Long mentoreId, Authentication authentication) {
        Staff currentUser = this.staffService.getByUsername(authentication.getName());
        Staff mentore = this.staffService.getById(mentoreId);

        Hackathon hackathon = this.hackathonService.getById(id);

        return ResponseEntity.ok(
                this.hackathonMapper.toResponse(
                        this.hackathonService.aggiungi(hackathon, mentore)
                )
        );
    }
}
