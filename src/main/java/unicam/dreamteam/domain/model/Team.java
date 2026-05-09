package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Access(AccessType.FIELD)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String descrizione;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Sottomissione> sottomissioni = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "team_utenti",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private final Set<Utente> membri = new HashSet<>();

    @ManyToMany
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

    public Team(String name) {
        this.nome = name;
    }

    public Team() {

    }

    public void aggiungiMembro(Utente utente) {
        if (utente.getTeam() != null) throw new IllegalStateException("L'utente è già in un team");
        membri.add(utente);
        utente.setTeam(this);
    }

    public void rimuoviMembro(Utente utente) {
        membri.remove(utente);
        utente.setTeam(null);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public Set<Utente> getMembri() { return membri; }
    public Set<Hackathon> getHackathons() { return hackathons; }
    public Set<Invito> getInviti() { return inviti; }
    public Set<Sottomissione> getSottomissioni() { return sottomissioni; }
    public Set<Segnalazione> getSegnalazioni() { return segnalazioni; }
    public Set<RichiestaSupporto> getRichiesteSupporto() { return richiesteSupporto; }

    public void setNome(String nome) { this.nome = nome; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

}
