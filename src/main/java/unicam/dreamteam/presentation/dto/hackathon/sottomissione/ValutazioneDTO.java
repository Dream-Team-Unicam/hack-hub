package unicam.dreamteam.presentation.dto.hackathon.sottomissione;

import unicam.dreamteam.presentation.dto.security.response.AccountResponse;

public record ValutazioneDTO(
        Long id,
        Integer punteggio,
        String giudizio,
        AccountResponse giudice
) {}
