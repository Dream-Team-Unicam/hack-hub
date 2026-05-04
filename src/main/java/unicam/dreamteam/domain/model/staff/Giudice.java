package unicam.dreamteam.domain.model.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import unicam.dreamteam.domain.model.Utente;
import unicam.dreamteam.domain.model.sottomissione.Valutazione;

import java.util.Set;

@Entity
@Table(name = "giudici")
public class Giudice extends Utente {
    private Set<Valutazione> valutazioni;
}
