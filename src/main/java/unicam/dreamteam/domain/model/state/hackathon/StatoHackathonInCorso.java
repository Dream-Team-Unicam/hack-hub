package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;

public class StatoHackathonInCorso implements StatoHackathon {
    @Override
    public boolean inviaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        // TODO: Impelementa invio sottomissione
        return false;
    }

    @Override
    public boolean aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        // TODO: Impelementa aggiorna sottomissione
        return false;
    }

    @Override
    public String getNome() {
        return "IN_CORSO";
    }
}
