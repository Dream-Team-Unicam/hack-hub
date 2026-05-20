package unicam.dreamteam.domain.service.team;

import jakarta.persistence.EntityNotFoundException;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.repository.InvitoRepository;
import unicam.dreamteam.infrastructure.repository.TeamRepository;
import unicam.dreamteam.infrastructure.repository.UtenteRepository;
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

    public Invito invita(Team team, Long idUtenteInvitato) {

        Optional<Utente> utenteInvitato = this.utenteRepository.findById(idUtenteInvitato);

        if (utenteInvitato.isEmpty()) throw new EntityNotFoundException(
                String.format(
                        "Utente.id=%s",
                        idUtenteInvitato
                )
        );

        if (this.invitoRepository.)

        Invito newInvito = new Invito(
                team,
                utenteInvitato.get()
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

    public boolean esisteInvitoUtenteTeam(Long idUtente, Long idTeam) {
        return this.invitoRepository.existsByUtenteIdAndTeamId(
                idUtente,
                idTeam
        );
    }
}
