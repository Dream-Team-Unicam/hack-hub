package unicam.dreamteam.presentation.dto.team.response;

import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import java.util.List;

public record TeamResponse(
        Long id,
        String nome,
        String descrizione,
        List<AccountResponse> membri
) {}