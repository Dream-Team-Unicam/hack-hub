package unicam.dreamteam.domain.model.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Utente;

import java.util.Set;

@Entity
@Table(name = "organizzatori")
public class Organizzatore extends Utente {
    private Set<Hackathon> hackathonsOrganizzati;
}
