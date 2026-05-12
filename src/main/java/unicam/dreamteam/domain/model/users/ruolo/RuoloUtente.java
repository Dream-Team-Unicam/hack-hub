package unicam.dreamteam.domain.model.users.ruolo;

import lombok.Getter;

import java.util.Set;

@Getter
public enum RuoloUtente implements Ruolo {
    UTENTE(Set.of(
            Permesso.CREA_TEAM
    )),
    TEAM_MEMBER(Set.of(
            Permesso.INVIA_SOTTOMISSIONE,
            Permesso.INVITA_NEL_TEAM
    )),
    TEAM_LEADER(Set.of(
            Permesso.GESTISCI_TEAM,
            Permesso.INVITA_NEL_TEAM,
            Permesso.ISCRIVI_TEAM_HACKATHON,
            Permesso.INVIA_SOTTOMISSIONE
    ));

    private final Set<Permesso> permessi;

    RuoloUtente(Set<Permesso> permessi) {
        this.permessi = permessi;
    }

    public String toAuthority() { return "ROLE_" + this.name(); }

    @Override
    public String getName() {
        return name();
    }
}
