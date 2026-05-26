package unicam.dreamteam.presentation.dto.hackathon.sottomissione;

import unicam.dreamteam.presentation.dto.team.response.TeamResponse;

import java.time.LocalDate;

public record SottomissioneDTO(
        Long id,
        LocalDate dataCaricamento,
        LocalDate dataUltimoAggiornamento,
        String contenuto,
        TeamResponse teamResponse,
        Long hackathonId,
        String hackathonNome,
        ValutazioneDTO valutazione
) {}
