package unicam.dreamteam.domain.model.staff;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import unicam.dreamteam.domain.model.Utente;

@Entity
@Table(name = "mentori")
public class Mentore extends Utente {
}
