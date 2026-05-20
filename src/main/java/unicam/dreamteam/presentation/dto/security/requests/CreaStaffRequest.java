package unicam.dreamteam.presentation.dto.security.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreaStaffRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String ruolo
) {

}
