package unicam.dreamteam.presentation.dto.team;

import jakarta.validation.constraints.NotBlank;
import unicam.dreamteam.domain.model.Invito;

import java.util.Set;

public record CreateTeamRequest (
        @NotBlank(message = "Team name è obbligatorio")
        String name,
        String descrizione,
        Set<Invito> inviti
) {}
