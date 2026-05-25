package unicam.dreamteam.domain.model.state.hackathon;


import unicam.dreamteam.domain.exception.hackathon.HackathonException;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.sottomissione.Valutazione;

public interface StatoHackathon {
    default void iscrivi(Hackathon hackathon, Team team) {
        throw new HackathonException("Iscrizioni non aperte");
    }
    default void inviaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new HackathonException("Hackathon non in corso");
    }
    default void valuta(Hackathon hackathon, Sottomissione sottomissione, Valutazione valutazione) {
        throw new HackathonException("Hackathon non in fase di valutazione");
    }
    default void concludi(Hackathon hackathon) {
        throw new HackathonException("Operazione non permessa in questo stato");
    }

    StatoHackathon prossimoStato();

    String getNome();
}
