package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.staff.Mentore;

import java.time.LocalDate;

@Entity
@Table(name = "segnalazioni")
@Access(AccessType.FIELD)
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(nullable = false)
    private LocalDate dataSegnalazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id", nullable = false)
    private Mentore mentore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team teamSegnalato;

    public Segnalazione(String descrizione, Mentore mentore, Team teamSegnalato) {
        this.descrizione = descrizione;
        this.mentore = mentore;
        this.teamSegnalato = teamSegnalato;
        this.dataSegnalazione = LocalDate.now();
    }

    public Segnalazione() {}

    public Long getId() { return id; }
    public String getDescrizione() { return descrizione; }
    public LocalDate getDataSegnalazione() { return dataSegnalazione; }
    public Mentore getMentore() { return mentore; }
    public Team getTeamSegnalato() { return teamSegnalato; }
}
