package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.presentation.dto.security.requests.CreaStaffRequest;
import unicam.dreamteam.presentation.dto.security.requests.LoginRequest;
import unicam.dreamteam.presentation.dto.security.requests.RegisterRequest;
import unicam.dreamteam.presentation.dto.security.response.TokenResponse;
import unicam.dreamteam.domain.service.security.SecurityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(
                    securityService.login(
                            request.username(),
                            request.password()
                    ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(
                    securityService.register(
                            request.username(),
                            request.email(),
                            request.password()
                    ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/register/staff")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TokenResponse> creaStaff(@Valid @RequestBody CreaStaffRequest request) {
        try {
            return ResponseEntity.ok(
                    securityService.creaStaff(
                            request.username(),
                            request.email(),
                            request.password(),
                            RuoloStaff.valueOf(request.ruolo().toUpperCase())
                    ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
