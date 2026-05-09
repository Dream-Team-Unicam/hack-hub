package unicam.dreamteam.domain.model.sottomissione;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sottomissioni")
@Access(AccessType.FIELD)
public class Sottomissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataCaricamento;

    @Column
    private LocalDate dataUltimoAggiornamento;

    @Column(nullable = false)
    private String contenuto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id", nullable = false)
    private Hackathon hackathon;

    @OneToOne(mappedBy = "sottomissione", cascade = CascadeType.ALL, orphanRemoval = true)
    private Valutazione valutazione;

    public Sottomissione(String contenuto, Team team, Hackathon hackathon) {
        this.contenuto = contenuto;
        this.team = team;
        this.hackathon = hackathon;
        this.dataCaricamento = LocalDate.now();
        this.dataUltimoAggiornamento = dataCaricamento;
    }

    public Sottomissione() {
    }

    public void aggiorna(String nuovoContenuto) {
        if (isValutata()) throw new RuntimeException("Non consentito aggiornare una sottomissione valutata.");
        this.contenuto = nuovoContenuto;
        this.dataUltimoAggiornamento = LocalDate.now();
    }


    public Long getId() {
        return id;
    }
    public String getContenuto() {
        return contenuto;
    }
    public LocalDate getDataCaricamento() {
        return dataCaricamento;
    }
    public LocalDate getDataUltimoAggiornamento() {
        return dataUltimoAggiornamento;
    }
    public Team getTeam() { return team; }
    public Hackathon getHackathon() {
        return hackathon;
    }
    public Valutazione getValutazione() {
        return valutazione;
    }

    public boolean isValutata() {
        return this.valutazione != null;
    }

    public void setValutazione(Valutazione valutazione) {
        if (!Objects.equals(valutazione.getSottomissione().getId(), this.id)) throw new RuntimeException("La valutazione non è assegnata alla sottomissione corretta.");
        this.valutazione = valutazione;
    }
}
