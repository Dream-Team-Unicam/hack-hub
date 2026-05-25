package unicam.dreamteam.presentation.dto.team.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;

import java.time.LocalDate;

public record InvitoResponse (
        Long id,
        TeamResponse team,
        AccountResponse utenteInvitato,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataInvito,
        String stato
) {}
