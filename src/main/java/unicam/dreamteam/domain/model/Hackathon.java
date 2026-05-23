package unicam.dreamteam.domain.model;

import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathon;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathonCreato;
import unicam.dreamteam.infrastructure.persistence.converter.StatoHackathonConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hackathons")
@Access(AccessType.FIELD)
@Getter
@Setter
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descrizione;

    @Column(nullable = false)
    private String regolamento;

    @Column(nullable = false)
    private LocalDate dataInizio;

    @Column(nullable = false)
    private LocalDate dataFine;

    @Column(nullable = false)
    private LocalDate dataScadenzaIscrizioni;

    private String luogo;
    private Double premioDenaro;
    private Integer dimMaxTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Staff organizzatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
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
    private StatoHackathon stato = new StatoHackathonCreato();

    public Hackathon() {
    }

    public Hackathon(
            Long id,
            String nome,
            String descrizione,
            String regolamento,
            LocalDate dataInizio,
            LocalDate dataFine,
            LocalDate dataScadenzaIscrizioni,
            String luogo,
            Double premioDenaro,
            Integer dimMaxTeam,
            Staff organizzatore,
            Staff giudice) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.regolamento = regolamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
        this.luogo = luogo;
        this.premioDenaro = premioDenaro;
        this.dimMaxTeam = dimMaxTeam;
        this.organizzatore = organizzatore;
        this.giudice = giudice;
    }

    public Hackathon(
            String nome,
            String descrizione,
            String regolamento,
            LocalDate dataInizio,
            LocalDate dataFine,
            LocalDate dataScadenzaIscrizioni,
            String luogo,
            Double premioDenaro,
            Integer dimMaxTeam,
            Staff organizzatore,
            Staff giudice) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.regolamento = regolamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
        this.luogo = luogo;
        this.premioDenaro = premioDenaro;
        this.dimMaxTeam = dimMaxTeam;
        this.organizzatore = organizzatore;
        this.giudice = giudice;
    }

    public void aggiungiMentore(Staff mentore) {
        mentori.add(mentore);
    }
    public void iscrivi(Team team) {
        stato.iscrivi(this, team);
    }
}
