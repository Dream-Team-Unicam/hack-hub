package unicam.dreamteam.presentation.mapper;

import lombok.AllArgsConstructor;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvitoMapper implements IMapper<Invito, InvitoResponse> {
    private TeamMapper teamMapper;
    private AccountMapper accountMapper;

    @Override
    public InvitoResponse toResponse(Invito invito) {
        return new InvitoResponse(
                invito.getId(),
                this.teamMapper.toResponse(invito.getTeam()),
                this.accountMapper.toResponse(invito.getUtente()),
                invito.getDataInvito(),
                invito.getStato().getNome()
        );
    }
}
