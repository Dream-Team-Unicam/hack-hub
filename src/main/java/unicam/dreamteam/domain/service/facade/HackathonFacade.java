package unicam.dreamteam.domain.service.facade;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.exception.hackathon.HackathonException;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.hackathon.HackathonService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;

@Service
@AllArgsConstructor
public class HackathonFacade {
    private final HackathonService hackathonService;
    private final TeamService teamService;
    private final InvitoService invitoService;
    private final UtenteValidator utenteValidator;

    public Hackathon iscriviTeam(Long hackathonId, Utente utente) {
        utenteValidator.validaInTeam(utente);

        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(utente.getTeam().getId());
        hackathon.iscrivi(team);

        this.teamService.save(team);
        return hackathonService.save(hackathon);
    }

    public Hackathon apriIscrizioni(Long hackathonId) {
        Hackathon hackathon = hackathonService.getById(hackathonId);
        hackathon.apriIscrizioni();
        return this.hackathonService.save(hackathon);
    }

    public Hackathon avviaHackathon(Long hackathonId) {
        Hackathon hackathon = hackathonService.getById(hackathonId);
        hackathon.avvia();
        return hackathonService.save(hackathon);
    }

    public Sottomissione inviaSottomissione(Long hackathonId, Utente utente, String contenuto) {
        utenteValidator.validaInTeam(utente);

        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(utente.getTeam().getId());

        // verifica che il team sia iscritto
        if (!hackathon.getTeamIscritti().contains(team))
            throw new HackathonException("Il team non è iscritto a questo hackathon");

        // verifica che non esista già una sottomissione per questo team
        hackathon.getSottomissioni().stream()
                .filter(s -> s.getTeam().equals(team))
                .findFirst()
                .ifPresent(s -> { throw new HackathonException("Sottomissione già esistente"); });

        Sottomissione sottomissione = new Sottomissione(contenuto, team, hackathon);
        hackathon.inviaSottomissione(sottomissione);

        return hackathonService.save(hackathon)
                .getSottomissioni().stream()
                .filter(s -> s.getTeam().equals(team))
                .findFirst()
                .orElseThrow();
    }

    public void proclamaVincitore(Long hackathonId, Long teamId) {
        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(teamId);

        hackathon.proclamaVincitore(team);
        hackathonService.save(hackathon);
    }
}
