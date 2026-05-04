package unicam.dreamteam.domain.model.state.hackathon;

import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;

public interface StatoHackathon {
    default boolean iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("ERRORE (Iscrizione Team Hackathon): Iscrizioni chiuse.");
    }

    default boolean inviaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new IllegalStateException("ERRORE (Invio Sottomissione): Hackathon non in corso.");
    }

    default boolean aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new IllegalStateException("ERRORE (Aggiorna Sottomissione): Hackathon non in corso.");
    }

    default boolean valutaSottomissione(Hackathon hackathon, Sottomissione Sottomissione, int punteggio) {
        throw new IllegalStateException("ERRORE (Valutazione Sottomissione): Valutazione non attiva.");
    }

    String getNome();
}
