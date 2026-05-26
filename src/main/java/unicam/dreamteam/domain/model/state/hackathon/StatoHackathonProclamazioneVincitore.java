package unicam.dreamteam.domain.model.state.hackathon;

public class StatoHackathonProclamazioneVincitore implements StatoHackathon {

    @Override
    public StatoHackathon prossimoStato() {
        return new StatoHackathonConcluso();
    }

    @Override
    public String getNome() { return "PROCLAMAZIONE_VINCITORE"; }
}
