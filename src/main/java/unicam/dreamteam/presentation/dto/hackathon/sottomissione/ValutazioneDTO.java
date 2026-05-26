package unicam.dreamteam.presentation.dto.hackathon.sottomissione;

public record ValutazioneDTO(
        Long id,
        Integer punteggio,
        String giudizio,
        Long giudiceId,
        String giudiceUsername
) {}
