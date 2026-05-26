package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;

public class StatoHackathonInCorso implements StatoHackathon {
        @Override
    public void inviaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        hackathon.getSottomissioni().add(sottomissione);

    }

    @Override
    public StatoHackathon prossimoStato() { return new StatoHackathonValutazione(); }

    @Override
    public String getNome() { return "IN_CORSO"; }
}
