package unicam.dreamteam.domain.service.team;

import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.state.invito.StatoInvito;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.repository.InvitoRepository;
import unicam.dreamteam.infrastructure.repository.TeamRepository;
import unicam.dreamteam.infrastructure.repository.UtenteRepository;

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

    public List<Invito> getByTeamId(Long idTeam) {
        if (this.teamRepository.existsById(idTeam))
            throw new EntityNotFoundException(
                    String.format("Team.id=%s", idTeam)
            );

        return this.invitoRepository.findByTeamId(idTeam);
    }

    public List<Invito> getInvitiByUtenteAndStato(Utente utenteInvitato, StatoInvito statoInvito) {
        return this.invitoRepository.
                findByUtenteIdAndStato(
                        utenteInvitato.getId(),
                        statoInvito
                );
    }
    public List<Invito> getInvitiByStato(StatoInvito statoInvito) {
        return this.invitoRepository.findByStato(statoInvito);
    }


    public boolean esisteInvitoUtenteTeam(Long idUtente, Long idTeam) {
        return this.invitoRepository.existsByUtenteIdAndTeamId(
                idUtente,
                idTeam
        );
    }
}
