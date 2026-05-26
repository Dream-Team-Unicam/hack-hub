package unicam.dreamteam.presentation.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;

@Component
@AllArgsConstructor
public class TeamMapper implements IMapper<Team, TeamResponse> {
    private final AccountMapper accountMapper;

    public TeamResponse toResponse(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getNome(),
                team.getDescrizione(),
                team.getMembri().stream()
                        .map(this.accountMapper::toResponse)
                        .toList()
        );
    }
}
