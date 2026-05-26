package unicam.dreamteam.domain.service.team;

import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.state.invito.StatoInvito;
import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.validator.UtenteValidator;
import unicam.dreamteam.domain.repository.InvitoRepository;
import unicam.dreamteam.domain.repository.TeamRepository;
import unicam.dreamteam.domain.repository.UtenteRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvitoService {
    private InvitoRepository invitoRepository;
    private TeamRepository teamRepository;
    private UtenteRepository utenteRepository;
    private UtenteValidator utenteValidator;

    public Invito invita(Team team, String usernameUtenteInvitato) {
        Optional<Utente> opUtenteInvitato = this.utenteRepository.findByUsername(usernameUtenteInvitato);
        if (opUtenteInvitato.isEmpty()) throw new EntityNotFoundException(
                String.format("Utente.username=%s", usernameUtenteInvitato));

        Utente utenteInvitato = opUtenteInvitato.get();

        if (this.invitoRepository.existsByUtenteIdAndTeamId(utenteInvitato.getId(), team.getId()))
            throw new EntityExistsException(String.format("Invito.Team.id=%s,Invito.Utente.id=%s, Invito.Utente.username=%s",
                    team.getId(),
                    utenteInvitato.getId(),
                    usernameUtenteInvitato
            ));

        Invito newInvito = new Invito(
                team,
                utenteInvitato
        );
        return this.invitoRepository.save(newInvito);
    }

    public Invito accetta(Invito invito) {
        this.utenteValidator.validaNotInTeam(invito.getUtente());
        invito.accetta();
        this.invitoRepository.save(invito);
        this.teamRepository.save(invito.getTeam());
        this.utenteRepository.save(invito.getUtente());

        return invito;
    }

    public Invito rifiuta(Invito invito) {
        invito.rifiuta();
        this.invitoRepository.save(invito);
        return invito;
    }

    public Invito getByTeamAndUtenteInvitato(Team team, Utente utente) {
        Optional<Invito> invito = this.invitoRepository.findAllByTeamIdAndUtenteId(
                team.getId(),
                utente.getId(),
                new StatoInvitoPendente()
        );

        if(invito.isEmpty()) throw new EntityNotFoundException(String.format(
                "Nessun invito pendente trovato tra il team(id=%s) e l'utente(username=%s)",
                team.getNome(),
                utente.getUsername()
        ));

        return invito.get();
    }

    public List<Invito> getAllByUtentesTeam(Utente utente) {
        this.utenteValidator.validaInTeam(utente);
        return this.invitoRepository.findByTeamIdAndStato(utente.getTeam().getId(), new StatoInvitoPendente());
    }

    public List<Invito> getAllByUtenteAndStato(Utente utenteInvitato, StatoInvito statoInvito) {
        return this.invitoRepository.
                findByUtenteIdAndStato(
                        utenteInvitato.getId(),
                        statoInvito
                );
    }
    public List<Invito> getAllByStato(StatoInvito statoInvito) {
        return this.invitoRepository.findByStato(statoInvito);
    }


    public boolean esisteInvitoUtenteTeam(Utente utente, Team team) {
        return this.invitoRepository.existsByUtenteIdAndTeamId(
                utente.getId(),
                team.getId()
        );
    }
}
