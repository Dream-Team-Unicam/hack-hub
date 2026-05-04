package unicam.dreamteam.domain.model.sottomissione;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.staff.Giudice;

@Entity
@Table(name = "valutazioni")
@Access(AccessType.FIELD)
public class Valutazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer punteggio;

    @Column(nullable = false, columnDefinition = "TEXT")
    private Integer giudizio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id", nullable = false)
    private Giudice giudice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sottomissione_id", nullable = false)
    private Sottomissione sottomissione;

    public Valutazione(Sottomissione sottomissione, Integer punti, Giudice giudice) {
        this.sottomissione = sottomissione;
        this.punteggio = punti;
        this.giudice = giudice;
    }

    public Valutazione() {}

    public Long getId() { return id; }
    public Integer getPunteggio() { return punteggio; }
    public Giudice getGiudice() { return giudice; }
    public Sottomissione getSottomissione() { return sottomissione; }
}
