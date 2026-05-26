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

    private final TeamMapper teamMapper;

    public SottomissioneDTO toResponse(Sottomissione sottomissione) {
        return new SottomissioneDTO(
                sottomissione.getId(),
                sottomissione.getDataCaricamento(),
                sottomissione.getDataUltimoAggiornamento(),
                sottomissione.getContenuto(),
                teamMapper.toResponse(sottomissione.getTeam()),
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
