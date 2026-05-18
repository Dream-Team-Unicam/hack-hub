package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.service.HackathonService;
import unicam.dreamteam.domain.service.UtenteService;
import unicam.dreamteam.presentation.dto.hackathon.CreaHackathonRequest;
import unicam.dreamteam.presentation.dto.HackathonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hackathons")
@AllArgsConstructor
public class HackathonController {
    private HackathonService hackathonService;
    private UtenteService utenteService;

    @GetMapping
    public List<HackathonDTO> getAllHackathons() {
        return this.hackathonService.getAllHackathons();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public HackathonDTO creaHackathon(@RequestBody CreaHackathonRequest dto) {

        // TODO: Implementare
        return null;
    }
}
