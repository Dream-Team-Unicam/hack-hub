package unicam.dreamteam.domain.model.users;

import unicam.dreamteam.domain.model.Team;

public interface MembroTeam {
    Team getTeam();
    void setTeam(Team team);
    default boolean hasTeam() {
        return getTeam() != null;
    }
}