package unicam.dreamteam.domain.model;

import unicam.dreamteam.domain.exception.team.TeamException;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.users.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Access(AccessType.FIELD)
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    private String nome; // TODO: Mettere un dispalyName e name(una sorta di username ma per il team)

    @Column
    @Setter
    private String descrizione;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Sottomissione> sottomissioni = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_utenti",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private final Set<Utente> membri = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_hackathons",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "hackathon_id")
    )
    private final Set<Hackathon> hackathons = new HashSet<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Invito> inviti = new HashSet<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<RichiestaSupporto> richiesteSupporto = new HashSet<>();

    @OneToMany(mappedBy = "teamSegnalato", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Segnalazione> segnalazioni = new HashSet<>();

    public Team() {
    }

    public Team(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public void aggiungiMembro(Utente utente) {
        if (utente.getTeam() != null) throw new TeamException("L'utente è già in un team");
        membri.add(utente);
        utente.setTeam(this);
    }

    public void rimuoviMembro(Utente utente) {
        membri.remove(utente);
        utente.setTeam(null);
    }
}
