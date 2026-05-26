package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;

public class StatoHackathonCreato implements StatoHackathon {
    @Override
    public StatoHackathon prossimoStato() {
        return new StatoHackathonIscrizione();
    }

    @Override
    public void apriIscrizioni(Hackathon hackathon) {
        hackathon.setStato(prossimoStato());
    }

    @Override
    public String getNome() {
        return "CREATO";
    }
}
