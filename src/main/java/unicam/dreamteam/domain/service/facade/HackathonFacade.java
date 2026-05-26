package unicam.dreamteam.domain.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.accounts.StaffService;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.hackathon.HackathonService;
import unicam.dreamteam.domain.service.hackathon.sottomissione.SottomissioneService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.HackathonValidator;
import unicam.dreamteam.domain.validator.UtenteValidator;

@Service
@AllArgsConstructor
public class HackathonFacade {
    private final HackathonService hackathonService;
    private final TeamService teamService;
    private final UtenteService utenteService;
    private final StaffService staffService;
    private final SottomissioneService sottomissioneService;

    private final HackathonValidator hackathonValidator;
    private final UtenteValidator utenteValidator;

    public Hackathon aggiungiMentore(Long hackathonId, Long mentoreId) {
        Staff mentore = this.staffService.getById(mentoreId);
        Hackathon hackathon = this.hackathonService.getById(hackathonId);

        this.hackathonValidator.validaHackathonNonHaMentore(hackathon, mentore);
        return hackathonService.aggiungiMentore(hackathon, mentore);
    }

    public Hackathon rimuoviMentore(Long hackathonId, Long mentoreId) {
        Staff mentore = this.staffService.getById(mentoreId);
        Hackathon hackathon = this.hackathonService.getById(hackathonId);

        this.hackathonValidator.validaHackathonHaMentore(hackathon, mentore);
        return hackathonService.rimuoviMentore(hackathon, mentore);
    }

    @Transactional
    public Hackathon iscriviTeam(Long hackathonId, String username) {
        Utente utente = this.utenteService.getByUsername(username);
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

    public Sottomissione inviaSottomissione(Long hackathonId, String username, String contenuto) {
        Utente utente = this.utenteService.getByUsername(username);
        this.utenteValidator.validaInTeam(utente);

        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(utente.getTeam().getId());

        this.hackathonValidator.validTeamIscrittoHackathon(hackathon, team);
        this.hackathonValidator.validaTeamNonHaInvitoSottomissione(hackathon, team);

        Sottomissione sottomissione = new Sottomissione(contenuto, team, hackathon);
        hackathon.inviaSottomissione(sottomissione);
        return sottomissioneService.save(sottomissione);
    }

    public Sottomissione aggiornaSottomissione(Long hackathonId, String username, String nuovoContenuto) {
        Utente utente = this.utenteService.getByUsername(username);
        utenteValidator.validaInTeam(utente);

        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(utente.getTeam().getId());

        Sottomissione sottomissione = this.sottomissioneService.getByHackathonAndTeam(hackathon, team);

        sottomissione.aggiorna(nuovoContenuto);
        return sottomissioneService.save(sottomissione);
    }

    public void proclamaVincitore(Long hackathonId, Long teamId) {
        Hackathon hackathon = hackathonService.getById(hackathonId);
        Team team = teamService.getById(teamId);

        hackathon.proclamaVincitore(team);
        hackathonService.save(hackathon);
    }
}
