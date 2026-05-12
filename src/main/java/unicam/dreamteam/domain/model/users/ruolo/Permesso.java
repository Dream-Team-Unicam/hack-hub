package unicam.dreamteam.domain.model.users.ruolo;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Permesso {
    ALL("Consente di eseguire tutte le azioni. (Mettere gli altri è superfluo)"),
    CREA_HACKATHON("Consente di creare un nuovo hackathon."),
    GESTISCI_HACKATHON("Consente di modificare/eliminare un hackathon esistente."),
    CREA_TEAM("Consente di creare un nuovo team."),
    GESTISCI_TEAM("Consente di modificare/eliminare un team esistente."),
    INVITA_NEL_TEAM("Consente di invitare utenti nel proprio team"),
    ISCRIVI_TEAM_HACKATHON("Consente di iscrivere un team ad un hackathon."),
    INVIA_SOTTOMISSIONE("Consente di inviare una sottomissione."),
    VALUTA_SOTTOMISSIONE("Consente di valutare le sottomissione."),
    SEGNALA_TEAM("Consente di segnalare un team all'organizzatore per violazione del regolamento.");

    private final String descrizione;

    Permesso(String descrizione) {
        this.descrizione = descrizione;
    }

    public static Optional<Permesso> fromString(String name) {
        return Arrays.stream(values())
                .filter(p -> p.name().equals(name))
                .findFirst();
    }
}
