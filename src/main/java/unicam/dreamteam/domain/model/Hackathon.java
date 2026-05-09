package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathon;
import unicam.dreamteam.domain.model.state.hackathon.StatoHackathonCreato;
import unicam.dreamteam.infrastructure.persistence.converter.StatoHackathonConverter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hackathons")
@Access(AccessType.FIELD)
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    private String luogo;

    @Column
    private Double premioDenaro;

    @Column
    private Integer dimMaxTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Utente organizzatore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
    private Utente giudice;

    @ManyToMany
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private Set<Utente> mentori = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vincitore_id")
    private Team vincitore;

    @ManyToMany(mappedBy = "hackathons")
    private Set<Team> teamIscritti = new HashSet<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sottomissione> sottomissioni = new HashSet<>();

    @Convert(converter = StatoHackathonConverter.class)
    @Column(nullable = false)
    private StatoHackathon stato = new StatoHackathonCreato();

    public Hackathon() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDate getDataScadenzaIscrizioni() {
        return dataScadenzaIscrizioni;
    }

    public void setDataScadenzaIscrizioni(LocalDate dataScadenzaIscrizioni) {
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Double getPremioDenaro() {
        return premioDenaro;
    }

    public void setPremioDenaro(Double premioDenaro) {
        this.premioDenaro = premioDenaro;
    }

    public Integer getDimMaxTeam() {
        return dimMaxTeam;
    }

    public void setDimMaxTeam(Integer dimMaxTeam) {
        this.dimMaxTeam = dimMaxTeam;
    }

    public void aggiungiMentore(Utente mentore) {
        mentori.add(mentore);
    }

    public Set<Team> getTeamIscritti() {
        return teamIscritti;
    }

    public void iscrivi(Team team) {
        stato.iscrivi(this, team);
    }

    public Set<Sottomissione> getSottomissioni() {
        return sottomissioni;
    }


    public StatoHackathon getStato() {
        return stato;
    }

    public void setStato(StatoHackathon stato) {
        this.stato = stato;
    }
}
