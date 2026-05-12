package unicam.dreamteam.domain.model.users.ruolo;

import lombok.Getter;

import java.util.Set;

@Getter
public enum RuoloStaff implements Ruolo {
    ADMIN(Set.of(
            Permesso.ALL
    )),
    ORGANIZZATORE(Set.of(
            Permesso.CREA_HACKATHON,
            Permesso.GESTISCI_HACKATHON
    )),
    GIUDICE(Set.of(
            Permesso.VALUTA_SOTTOMISSIONE
    )),
    MENTORE(Set.of(
            Permesso.SEGNALA_TEAM
    ));

    private final Set<Permesso> permessi;

    RuoloStaff(Set<Permesso> permessi) {
        this.permessi = permessi;
    }

    public String toAuthority() { return "ROLE_" + this.name(); }

    @Override
    public String getName() {
        return name();
    }
}
