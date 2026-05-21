package unicam.dreamteam.presentation.dto.security.response;

public record AccountResponse(
        Long id,
        String username,
        String email,
        String ruolo
) { }
