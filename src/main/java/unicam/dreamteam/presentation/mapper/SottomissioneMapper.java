package unicam.dreamteam.presentation.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.model.sottomissione.Valutazione;
import unicam.dreamteam.presentation.dto.hackathon.sottomissione.SottomissioneDTO;
import unicam.dreamteam.presentation.dto.hackathon.sottomissione.ValutazioneDTO;

@Component
@AllArgsConstructor
public class SottomissioneMapper {

    public SottomissioneDTO toResponse(Sottomissione sottomissione) {
        return new SottomissioneDTO(
                sottomissione.getId(),
                sottomissione.getDataCaricamento(),
                sottomissione.getDataUltimoAggiornamento(),
                sottomissione.getContenuto(),
                sottomissione.getTeam().getId(),
                sottomissione.getTeam().getNome(),
                sottomissione.getHackathon().getId(),
                sottomissione.getHackathon().getNome(),
                sottomissione.getValutazione() == null ? null : toValutazioneDTO(sottomissione.getValutazione())
        );
    }

    private ValutazioneDTO toValutazioneDTO(Valutazione valutazione) {
        return new ValutazioneDTO(
                valutazione.getId(),
                valutazione.getPunteggio(),
                valutazione.getGiudizio(),
                valutazione.getGiudice().getId(),
                valutazione.getGiudice().getUsername()
        );
    }
}
