package unicam.dreamteam.presentation.dto.hackathon.sottomissione;

import java.time.LocalDate;

public record SottomissioneDTO(
        Long id,
        LocalDate dataCaricamento,
        LocalDate dataUltimoAggiornamento,
        String contenuto,
        Long teamId,
        String teamNome,
        Long hackathonId,
        String hackathonNome,
        ValutazioneDTO valutazione
) {}
