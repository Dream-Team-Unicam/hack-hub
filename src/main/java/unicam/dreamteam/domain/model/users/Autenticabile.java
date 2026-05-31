package unicam.dreamteam.domain.model.users;

import unicam.dreamteam.domain.model.users.ruolo.Ruolo;

public interface Autenticabile {
    Long getId();
    String getUsername();
    String getPassword();
    String getEmail();
    Ruolo getRuolo();
}
