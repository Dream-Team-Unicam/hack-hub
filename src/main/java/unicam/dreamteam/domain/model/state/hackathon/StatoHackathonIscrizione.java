package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;

public class StatoHackathonIscrizione implements StatoHackathon {
    @Override
    public void iscrivi(Hackathon hackathon, Team team) {
        hackathon.getTeamIscritti().add(team);
        team.getHackathons().add(hackathon);
    }

    @Override
    public StatoHackathon prossimoStato() { return new StatoHackathonInCorso(); }

    @Override
    public String getNome() { return "ISCRIZIONE"; }
}
