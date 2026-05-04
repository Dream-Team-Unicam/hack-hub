package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;

public class StatoHackathonIscrizione implements StatoHackathon {
    @Override
    public boolean iscriviTeam(Hackathon hackathon, Team team) {
        // TODO: Implementare iscrizione team all'hackathon
        return false;
    }

    @Override
    public String getNome() {
        return "ISCRIZIONE";
    }
}
