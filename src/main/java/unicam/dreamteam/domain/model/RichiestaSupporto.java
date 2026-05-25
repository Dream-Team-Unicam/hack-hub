package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import unicam.dreamteam.domain.model.state.ticket.StatoRichiestaSupporto;
import unicam.dreamteam.domain.model.users.Staff;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "richieste_supporto")
@Getter
// TODO: USO LOMBOK
public class RichiestaSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String oggetto;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private LocalDate dataApertura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentore_id")
    private Staff mentore;

    @ManyToOne(fetch = FetchType.EAGER)
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


    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setDataApertura(LocalDate dataApertura) {
        this.dataApertura = dataApertura;
    }
}
