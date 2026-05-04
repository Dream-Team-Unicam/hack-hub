package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import unicam.dreamteam.domain.model.state.ticket.StatoRichiestaSupporto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "richieste_supporto")
public class RichiestaSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String oggetto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(nullable = false)
    private LocalDate dataApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id")
    private Utente mentore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "richiestaSupporto", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Call> calls  = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoRichiestaSupporto stato; // TODO: Cambiare in State Pattern?

    public RichiestaSupporto() {

    }

    public RichiestaSupporto(String oggetto, String descrizione, LocalDate dataApertura, StatoRichiestaSupporto stato) {
        this.oggetto = oggetto;
        this.descrizione = descrizione;
        this.dataApertura = dataApertura;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(LocalDate dataApertura) {
        this.dataApertura = dataApertura;
    }

    public StatoRichiestaSupporto getStato() {
        return stato;
    }
}
