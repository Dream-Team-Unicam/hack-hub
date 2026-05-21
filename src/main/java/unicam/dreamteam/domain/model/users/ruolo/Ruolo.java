package unicam.dreamteam.domain.model.users.ruolo;

import java.util.Arrays;

public interface Ruolo {
    /**
     * Restituisce la rappresentazione testuale del ruolo
     * utilizzata come authority nel sistema di sicurezza.
     *
     * @return la authority associata al ruolo
     */
    String toAuthority();

    String getName();

}