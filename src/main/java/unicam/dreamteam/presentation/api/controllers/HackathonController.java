package unicam.dreamteam.presentation.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unicam.dreamteam.domain.service.HackathonService;
import unicam.dreamteam.presentation.dto.CreaHackathonDTO;
import unicam.dreamteam.presentation.dto.HackathonDTO;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/hackathons")
public class HackathonController {
    @Autowired
    private HackathonService hackathonService;

    @GetMapping
    public List<HackathonDTO> getAllHackathons() {
        return this.hackathonService.getAllHackathons();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HackathonDTO creaHackathon(@RequestBody CreaHackathonDTO dto) {

        // TODO: Implementare
        return null;
    }
}
