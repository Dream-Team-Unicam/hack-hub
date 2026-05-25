package unicam.dreamteam.domain.model.builder;

import unicam.dreamteam.domain.exception.team.TeamException;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.users.Staff;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HackathonBuilder implements Builder<Hackathon> {

    // Campi obbligatori
    private String nome;
    private String regolamento;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private LocalDate dataScadenzaIscrizioni;
    private Staff organizzatore;
    private Staff giudice;

    // Campi opzionali
    private String descrizione;
    private String luogo;
    private Double premioDenaro;
    private Integer dimMaxTeam;
    private final Set<Staff> mentori = new HashSet<>();


    public HackathonBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public HackathonBuilder descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public HackathonBuilder regolamento(String regolamento) {
        this.regolamento = regolamento;
        return this;
    }

    public HackathonBuilder dataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public HackathonBuilder dataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public HackathonBuilder dataScadenzaIscrizioni(LocalDate dataScadenzaIscrizioni) {
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
        return this;
    }

    public HackathonBuilder luogo(String luogo) {
        this.luogo = luogo;
        return this;
    }

    public HackathonBuilder premioDenaro(Double premioDenaro) {
        this.premioDenaro = premioDenaro;
        return this;
    }

    public HackathonBuilder dimMaxTeam(Integer dimMaxTeam) {
        this.dimMaxTeam = dimMaxTeam;
        return this;
    }

    public HackathonBuilder organizzatore(Staff organizzatore) {
        this.organizzatore = organizzatore;
        return this;
    }

    public HackathonBuilder giudice(Staff giudice) {
        this.giudice = giudice;
        return this;
    }

    public HackathonBuilder aggiungiMentore(Staff mentore) {
        Objects.requireNonNull(mentore, "Il mentore non può essere null");
        this.mentori.add(mentore);
        return this;
    }

    public HackathonBuilder mentori(Set<Staff> mentori) {
        Objects.requireNonNull(mentori, "L'insieme dei mentori non può essere null");
        this.mentori.clear();
        this.mentori.addAll(mentori);
        return this;
    }

    @Override
    public Hackathon build() {
        validate();

        Hackathon hackathon = new Hackathon(
                this.nome,
                this.descrizione,
                this.regolamento,
                this.dataInizio,
                this.dataFine,
                this.dataScadenzaIscrizioni,
                this.luogo,
                this.premioDenaro,
                this.dimMaxTeam,
                this.organizzatore,
                this.giudice
        );

        this.mentori.forEach(hackathon::aggiungiMentore);
        return hackathon;
    }

    private void validate() {
        Objects.requireNonNull(nome, "Il nome è obbligatorio");
        Objects.requireNonNull(regolamento, "Il regolamento è obbligatorio");
        Objects.requireNonNull(dataInizio, "La data di inizio è obbligatoria");
        Objects.requireNonNull(dataFine, "La data di fine è obbligatoria");
        Objects.requireNonNull(dataScadenzaIscrizioni, "La data di scadenza iscrizioni è obbligatoria");
        Objects.requireNonNull(organizzatore, "L'organizzatore è obbligatorio");

        if (nome.isBlank()) {
            throw new IllegalStateException("Il nome non può essere vuoto");
        }
        if (regolamento.isBlank()) {
            throw new IllegalStateException("Il regolamento non può essere vuoto");
        }
        if (dataFine.isBefore(dataInizio)) {
            throw new IllegalStateException(
                    "La data di fine non può precedere la data di inizio");
        }
        if (!dataScadenzaIscrizioni.isBefore(dataInizio)) {
            throw new IllegalStateException(
                    "La scadenza delle iscrizioni deve precedere la data di inizio");
        }
        if (premioDenaro != null && premioDenaro < 0) {
            throw new IllegalStateException("Il premio in denaro non può essere negativo");
        }
        if (dimMaxTeam != null && dimMaxTeam <= 0) {
            throw new TeamException("La dimensione massima del team deve essere positiva");
        }
    }
}