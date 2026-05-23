package unicam.dreamteam.presentation.dto.hackathon;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HackathonCreationRequest (
        @NotBlank
        @NotNull
        String nome,
        String descrizione,

        @NotBlank
        @NotNull String regolamento,

        @NotBlank
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInizio,

        @NotBlank
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFine,

        @NotBlank
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataScadenzaIscrizioni,

        @NotNull
        String luogo,

        @NotNull
        Double premioDenaro,

        @NotNull
        Integer dimMaxTeam,

        @NotNull
        Long giudiceId
) {}
