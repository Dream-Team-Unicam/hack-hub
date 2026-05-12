package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathon;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathonCreato;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.persistence.converter.StatoHackathonConverter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hackathons")
@Access(AccessType.FIELD)
@Getter
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    @Column
    private String descrizione;

    @Setter
    @Column(nullable = false)
    private String regolamento;

    @Setter
    @Column(nullable = false)
    private LocalDate dataInizio;

    @Setter
    @Column(nullable = false)
    private LocalDate dataFine;

    @Setter
    @Column(nullable = false)
    private LocalDate dataScadenzaIscrizioni;

    @Setter
    private String luogo;
    @Setter
    private Double premioDenaro;
    @Setter
    private Integer dimMaxTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Staff organizzatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
    @Setter
    private Staff giudice;

    @ManyToMany
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private Set<Staff> mentori = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vincitore_id")
    private Team teamVincitore;

    @ManyToMany(mappedBy = "hackathons")
    private Set<Team> teamIscritti = new HashSet<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sottomissione> sottomissioni = new HashSet<>();

    @Convert(converter = StatoHackathonConverter.class)
    @Column(nullable = false)
    @Setter
    private StatoHackathon stato = new StatoHackathonCreato();

    public Hackathon() {
    }


    public void aggiungiMentore(Staff mentore) {
        mentori.add(mentore);
    }

    public void iscrivi(Team team) {
        stato.iscrivi(this, team);
    }

}
