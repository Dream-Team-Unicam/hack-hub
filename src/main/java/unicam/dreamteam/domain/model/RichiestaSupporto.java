package unicam.dreamteam.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import unicam.dreamteam.domain.model.state.ticket.StatoRichiestaSupporto;
import unicam.dreamteam.domain.model.users.Staff;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "richieste_supporto")
@Getter
@Setter
public class RichiestaSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String oggetto;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private LocalDate dataApertura;

    private LocalDateTime dataInvio;

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
    private StatoRichiestaSupporto stato;

    public RichiestaSupporto() {
    }

    public RichiestaSupporto(String oggetto, String descrizione, LocalDate dataApertura, StatoRichiestaSupporto stato) {
        this.oggetto = oggetto;
        this.descrizione = descrizione;
        this.dataApertura = dataApertura;
        this.stato = stato;
    }
}
