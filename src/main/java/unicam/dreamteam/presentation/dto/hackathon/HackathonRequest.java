package unicam.dreamteam.presentation.dto.hackathon;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HackathonRequest(
        @NotBlank @NotNull String nome,
        String descrizione,
        @NotBlank @NotNull String regolamento,
        @JsonFormat(pattern = "dd/MM/yyyy") @NotNull LocalDate dataInizio,
        @JsonFormat(pattern = "dd/MM/yyyy")  @NotNull LocalDate dataFine,
        @JsonFormat(pattern = "dd/MM/yyyy")  @NotNull LocalDate dataScadenzaIscrizioni,
        @NotNull String luogo,
        @NotNull Double premioDenaro,
        @NotNull Integer dimMaxTeam,
        Long giudiceId
) {}