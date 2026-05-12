package unicam.dreamteam.presentation.dto.security;

public record TokenResponse(
        String accessToken,
        String expiration,
        String type
) {}
