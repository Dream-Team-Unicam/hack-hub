package unicam.dreamteam.presentation.dto.security.response;

public record TokenResponse(
        String accessToken,
        String expiration,
        String type
) {
}
