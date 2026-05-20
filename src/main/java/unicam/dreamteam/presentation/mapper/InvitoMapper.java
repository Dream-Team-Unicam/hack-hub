package unicam.dreamteam.presentation.mapper;

import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;

@Component
public class InvitoMapper {
    public InvitoResponse toResponse(Invito invito) {
        return new InvitoResponse(
                invito.getId(),
                invito.getTeam().getId(),
                invito.getUtente().getId(),
                invito.getDataInvito(),
                invito.getStato().getNome()
        );
    }
}
