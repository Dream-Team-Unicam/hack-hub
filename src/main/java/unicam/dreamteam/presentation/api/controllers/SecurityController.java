package unicam.dreamteam.presentation.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unicam.dreamteam.domain.service.security.SecurityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import unicam.dreamteam.presentation.dto.security.requests.LoginRequest;
import unicam.dreamteam.presentation.dto.security.requests.RegisterRequest;
import unicam.dreamteam.presentation.dto.security.response.TokenResponse;

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
    public ResponseEntity<TokenResponse> registerStaff(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(
                    securityService.registerStaff(
                            request.username(),
                            request.email(),
                            request.password()
                    ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
