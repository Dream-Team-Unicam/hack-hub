package unicam.dreamteam.domain.model.users.ruolo;

import java.util.Optional;
import java.util.Set;

/**
 * Rappresenta un ruolo utente all'interno del sistema.
 * <p>
 * Un ruolo definisce un insieme di {@link Permesso permessi}
 * che determinano le operazioni consentite a un utente.
 * Ogni implementazione di questa interfaccia deve specificare
 * i permessi associati al ruolo e la relativa authority utilizzata
 * dal sistema di autenticazione/autorizzazione.
 */
public interface Ruolo {

    /**
     * Verifica se il ruolo possiede il permesso identificato
     * dal nome fornito.
     * <p>
     * Il nome viene convertito in un oggetto {@link Permesso}
     * tramite {@link Permesso#fromString(String)}.
     *
     * @param nome il nome del permesso da verificare
     * @return {@code true} se il ruolo possiede il permesso,
     *         {@code false} altrimenti oppure se il nome non è valido
     */
    default boolean hasPermesso(String nome) {
        Optional<Permesso> optionalPermesso = Permesso.fromString(nome);
        return optionalPermesso.filter(this::hasPermesso).isPresent();
    }

    /**
     * Verifica se il ruolo possiede il permesso specificato.
     * <p>
     * Se il ruolo contiene il permesso speciale {@link Permesso#ALL},
     * allora ogni permesso viene considerato autorizzato.
     *
     * @param permesso il permesso da verificare
     * @return {@code true} se il ruolo possiede il permesso richiesto,
     *         {@code false} altrimenti
     */
    default boolean hasPermesso(Permesso permesso) {
        if (this.getPermessi().contains(Permesso.ALL)) return true;
        return this.getPermessi().contains(permesso);
    }

    /**
     * Restituisce l'insieme dei permessi associati al ruolo.
     *
     * @return insieme dei permessi del ruolo
     */
    Set<Permesso> getPermessi();

    /**
     * Restituisce la rappresentazione testuale del ruolo
     * utilizzata come authority nel sistema di sicurezza.
     *
     * @return la authority associata al ruolo
     */
    String toAuthority();

    String getName();
}