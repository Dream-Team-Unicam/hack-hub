package unicam.dreamteam.domain.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.domain.model.users.ruolo.RuoloUtente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.security.Autenticabile;
import unicam.dreamteam.domain.service.security.SecurityService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.domain.service.team.TeamService;
import unicam.dreamteam.domain.validator.UtenteValidator;

import java.util.List;

@Service
@AllArgsConstructor
public class InvitoFacade {
    private final SecurityService securityService;
    private final InvitoService invitoService;
    private final UtenteService utenteService;
    private final TeamService teamService;

    private UtenteValidator utenteValidator;

    public List<Invito> getAllByPendentiByAccountUsername(String username) {
        Autenticabile account = this.securityService.getAccountByUsername(username);
        if (account.getRuolo() == RuoloStaff.ADMIN) return getAllPendenti();
        if (account.getRuolo() == RuoloUtente.UTENTE) return getAllPendentiByUtente((Utente) account);
        return List.of(); // :)
    }

    public List<Invito> getAllPendenti() {
        return this.invitoService.getAllByStato(new StatoInvitoPendente());
    }

    public List<Invito> getAllPendentiByUtenteUsername(String username) {
        Utente utente = this.utenteService.getByUsername(username);
        return getAllPendentiByUtente(utente);
    }

    public List<Invito> getAllPendentiByUtente(Utente utente) {
        return this.invitoService.getAllByUtenteAndStato(utente, new StatoInvitoPendente());
    }

    public List<Invito> getAllPendentiTeamByUtenteUsername(String username) {
        Utente utente = this.utenteService.getByUsername(username);
        return getAllPendentiTeamByUtente(utente);
    }

    public List<Invito> getAllPendentiTeamByUtente(Utente utente) {
        return this.invitoService.getAllByUtentesTeam(utente);
    }

    public Invito invita(String usernameUtenteMembroTeam, String usernameUtenteInvitato) {
        Utente currentUser = this.utenteService.getByUsername(usernameUtenteMembroTeam);
        this.utenteValidator.validaInTeam(currentUser);

        Utente utenteInvitato = this.utenteService.getByUsername(usernameUtenteInvitato);

        return this.invitoService.invita(currentUser.getTeam(), utenteInvitato);
    }

    public Invito accetta(Long teamId, String username) {
        Utente utente = this.utenteService.getByUsername(username);
        this.utenteValidator.validaNotInTeam(utente);

        Team team = this.teamService.getById(teamId);

        return this.invitoService.accetta(
                this.invitoService.getByTeamAndUtenteInvitato(team, utente)
        );
    }
    public Invito rifiuta(Long teamId, String username) {
        Utente utente = this.utenteService.getByUsername(username);
        Team team = this.teamService.getById(teamId);

        return this.invitoService.rifiuta(
                this.invitoService.getByTeamAndUtenteInvitato(team, utente)
        );
    }
}
