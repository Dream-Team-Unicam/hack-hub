package unicam.dreamteam.domain.model.state.hackathon;

public class StatoHackathonConcluso implements StatoHackathon {
    @Override
    public StatoHackathon prossimoStato() {
        throw new IllegalStateException("L'hackathon è già concluso");
    }

    @Override
    public String getNome() {
        return "CONCLUSO";
    }
}
