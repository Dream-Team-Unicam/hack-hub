package unicam.dreamteam.domain.model.state.hackathon;

public class StatoHackathonCreato implements StatoHackathon {
    @Override
    public StatoHackathon prossimoStato() {
        return new StatoHackathonIscrizione();
    }

    @Override
    public String getNome() {
        return "CREATO";
    }
}
