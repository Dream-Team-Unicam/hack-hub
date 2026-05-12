package unicam.dreamteam.domain.service.team;

import unicam.dreamteam.domain.model.Invito;

import java.util.Set;

public interface ITeamService {
    String createTeam(Long userId, String teamName, Set<Invito> inviti);
}
