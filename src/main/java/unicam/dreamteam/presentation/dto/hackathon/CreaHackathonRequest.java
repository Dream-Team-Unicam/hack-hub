package unicam.dreamteam.presentation.dto.hackathon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreaHackathonRequest(
        @NotBlank @NotNull String nome,
        String descrizione,
        @NotBlank @NotNull String regolamento,
        @NotNull LocalDate dataInizio,
        @NotNull LocalDate dataFine,
        @NotNull LocalDate dataScadenzaIscrizioni,
        @NotNull String luogo,
        @NotNull Double premioDenaro,
        @NotNull Integer dimMaxTeam,
        @NotNull Long organizzatoreId,
        Long giudiceId
) {}
