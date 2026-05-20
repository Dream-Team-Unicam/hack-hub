package unicam.dreamteam.presentation.dto.team.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record InvitoResponse (
        Long id,
        Long idTeam,
        Long idUtenteInvitato,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataInvito,
        String stato
) {}
