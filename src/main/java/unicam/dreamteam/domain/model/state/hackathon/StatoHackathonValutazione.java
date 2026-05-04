package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;

public class StatoHackathonValutazione implements StatoHackathon {
    @Override
    public boolean valutaSottomissione(Hackathon hackathon, Sottomissione sottomissione, int punteggio) {
        // TODO: Implementare valutazione sottomissione
        return false;
    }

    @Override
    public String getNome() {
        return "VALUTAZIONE";
    }
}
