package unicam.dreamteam.domain.exception.ruolo;

import unicam.dreamteam.domain.model.users.ruolo.Ruolo;

public class RuoloNonAutorizzatoException extends RuntimeException {
    public RuoloNonAutorizzatoException(Ruolo ruolo, Ruolo ruoloAtteso) {
        super(String.format(
                "Account selezionato con ruolo '%s', ma era atteso il ruolo '%s'.",
                ruolo.getName(),
                ruoloAtteso.getName()
        ));
    }
}