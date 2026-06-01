package unicam.dreamteam.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import unicam.dreamteam.domain.model.entity.users.Staff;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "segnalazioni")
@Access(AccessType.FIELD)
@Getter
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private LocalDateTime dataSegnalazione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentore_id", nullable = false)
    private Staff mentore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = false)
    private Team teamSegnalato;

    public Segnalazione(String descrizione, Staff mentore, Team teamSegnalato) {
        this.descrizione = descrizione;
        this.mentore = mentore;
        this.teamSegnalato = teamSegnalato;
        this.dataSegnalazione = LocalDateTime.now();
    }

    public Segnalazione() {}
}
