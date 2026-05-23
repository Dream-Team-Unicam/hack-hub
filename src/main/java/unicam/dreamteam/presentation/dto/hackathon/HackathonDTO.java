package unicam.dreamteam.presentation.dto.hackathon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HackathonDTO {
    private Long id;
    @NotBlank private String nome;
    private String descrizione;
    @NotBlank private String regolamento;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dataInizio;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dataFine;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dataScadenzaIscrizioni;
    @NotNull private String luogo;
    @NotNull private Double premioDenaro;
    @NotNull private Integer dimMaxTeam;
    private Long giudiceId;
    private String stato;
    private AccountResponse organizzatore;
    private AccountResponse giudice;
    private List<AccountResponse> mentori;
    private List<TeamResponse> teamIscritti;
    private TeamResponse teamVincitore;

    // costruttore per request
    public HackathonDTO(String nome, String descrizione, String regolamento,
                        LocalDate dataInizio, LocalDate dataFine, LocalDate dataScadenzaIscrizioni,
                        String luogo, Double premioDenaro, Integer dimMaxTeam, Long giudiceId) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.regolamento = regolamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
        this.luogo = luogo;
        this.premioDenaro = premioDenaro;
        this.dimMaxTeam = dimMaxTeam;
        this.giudiceId = giudiceId;
    }

    // costruttore per response
    public HackathonDTO(Long id, String nome, String descrizione, String regolamento,
                        LocalDate dataInizio, LocalDate dataFine, LocalDate dataScadenzaIscrizioni,
                        String luogo, Double premioDenaro, Integer dimMaxTeam,
                        String stato, AccountResponse organizzatore, AccountResponse giudice,
                        List<AccountResponse> mentori, List<TeamResponse> teamIscritti,
                        TeamResponse teamVincitore) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.regolamento = regolamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
        this.luogo = luogo;
        this.premioDenaro = premioDenaro;
        this.dimMaxTeam = dimMaxTeam;
        this.stato = stato;
        this.organizzatore = organizzatore;
        this.giudice = giudice;
        this.mentori = mentori;
        this.teamIscritti = teamIscritti;
        this.teamVincitore = teamVincitore;
    }

}
