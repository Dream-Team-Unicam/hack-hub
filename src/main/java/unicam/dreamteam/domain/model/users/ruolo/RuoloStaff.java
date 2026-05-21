package unicam.dreamteam.domain.model.users.ruolo;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;

@Getter
public enum RuoloStaff implements Ruolo {
    ADMIN,
    ORGANIZZATORE,
    GIUDICE,
    MENTORE;

    public String toAuthority() { return "ROLE_" + this.name(); }

    @Override
    public String getName() {
        return name();
    }
}
