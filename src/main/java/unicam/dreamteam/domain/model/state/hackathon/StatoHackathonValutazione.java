package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.sottomissione.Valutazione;

public class StatoHackathonValutazione implements StatoHackathon {

    @Override
    public void valuta(Hackathon hackathon, Sottomissione sottomissione, Valutazione valutazione) {
        sottomissione.setValutazione(valutazione);
    }

    @Override
    public void concludi(Hackathon hackathon) {
        boolean tutteValutate = hackathon.getSottomissioni().stream()
                .allMatch(s -> s.getValutazione() != null);
        if (!tutteValutate)
            throw new IllegalStateException("Non tutte le sottomissioni sono state valutate");
        hackathon.setStato(prossimoStato());
    }

    @Override
    public StatoHackathon prossimoStato() { return new StatoHackathonConcluso(); }

    @Override
    public String getNome() { return "VALUTAZIONE"; }
}
