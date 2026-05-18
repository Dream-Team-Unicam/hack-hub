package unicam.dreamteam.domain.service.security;

import unicam.dreamteam.presentation.dto.security.response.TokenResponse;

public interface IAuthentication {
    TokenResponse login(String username, String password);
    TokenResponse register(String username, String email, String password);
}
