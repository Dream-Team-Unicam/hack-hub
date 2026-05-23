package unicam.dreamteam.domain.validator;

import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.exception.team.UserNotInATeamException;

import org.springframework.stereotype.Component;

@Component
public class UtenteValidator implements Validator<Utente> {

    @Override
    public void valida(Utente utente, Utente utenteAtteso) {
        if (utente.equals(utenteAtteso)) return;

        throw new RuntimeException(
                String.format(
                        "Utente.username=%s, UtenteAtteso.username=%s",
                        utente.getUsername(),
                        utenteAtteso.getUsername()
                )
        );
    }

    public void validInTeam(Utente utente) {
        if (!utente.hasTeam()) throw new UserNotInATeamException(
                String.format("Utente.username=%s", utente.getUsername())
        );
    }
}
