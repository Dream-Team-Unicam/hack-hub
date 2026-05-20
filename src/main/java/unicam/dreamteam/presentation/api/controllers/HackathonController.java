package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.service.HackathonService;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.presentation.dto.hackathon.HackathonRequest;
import unicam.dreamteam.presentation.dto.hackathon.HackathonResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import unicam.dreamteam.domain.model.Hackathon;
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

    @GetMapping
    public List<HackathonResponse> getAllHackathons() {
        //return this.hackathonService.getAllHackathons().forEach((hackathon) -> hackathonService.toResponse(hackathon));
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public HackathonResponse creaHackathon(@RequestBody HackathonRequest request, Authentication authentication) {
        Staff currentUser = this.staffService.getByUsername(authentication.getName()).orElseThrow();
        Hackathon newHackathon = this.hackathonService.createHackathon(request, currentUser);
        return hackathonService.toResponse(newHackathon);
    }
}
