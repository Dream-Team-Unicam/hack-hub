package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.state.invito.StatoInvito;
import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.infrastructure.persistence.converter.StatoInvitoConverter;

import java.time.LocalDate;

@Entity
@Table(name = "inviti")
@Access(AccessType.FIELD)
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInvito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Convert(converter = StatoInvitoConverter.class)
    @Column(nullable = false)
    private StatoInvito stato;

    public Invito(Team team, Utente destinatario) {
        this.team = team;
        this.utente = destinatario;
        this.dataInvito = LocalDate.now();
        this.stato = new StatoInvitoPendente();
    }

    public Invito() {
    }

    public void accetta() {
        this.stato = this.stato.accetta();
        this.team.aggiungiMembro(this.utente);
    }

    public void rifiuta() {
        this.stato = this.stato.rifiuta();
    }

    public Long getId() { return id; }
    public LocalDate getDataInvito() { return dataInvito; }
    public Team getTeam() { return team; }
    public Utente getUtente() { return utente; }
    public StatoInvito getStato() { return stato; }
}
