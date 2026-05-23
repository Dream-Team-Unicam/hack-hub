package unicam.dreamteam.presentation.mapper;

import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.presentation.dto.team.response.TeamResponse;

@Component
public class TeamMapper implements IMapper<Team, TeamResponse> {
    public TeamResponse toResponse(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getNome(),
                team.getDescrizione()
        );
    }
}
