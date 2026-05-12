package unicam.dreamteam.domain.service.security;

import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.repository.StaffRepository;
import unicam.dreamteam.domain.repository.UtenteRepository;
import unicam.dreamteam.domain.service.security.token.TokenProvider;
import unicam.dreamteam.presentation.dto.security.TokenResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SecurityService implements IAuthentication {
    private final TokenProvider tokenProvider;
    private final UtenteRepository utenteRepository;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(String username, String password) {
        Autenticabile account = utenteRepository.findByUsername(username)
                .map(u -> (Autenticabile) u)
                .or(() -> staffRepository.findByUsername(username).map(s -> (Autenticabile) s))
                .orElseThrow(() -> new BadCredentialsException("Credenziali Errate!"));

        if (!passwordEncoder.matches(password, account.getPassword()))
            throw new BadCredentialsException("Credenziali Errate!");

        return getTokenResponse(account);
    }

    @Override
    public TokenResponse register(String username, String email, String password) {
        if (utenteRepository.existsByUsername(username) || staffRepository.existsByUsername(username))
            throw new BadCredentialsException("Username già in uso.");

        if (utenteRepository.existsByEmail(email) || staffRepository.existsByEmail(email))
            throw new BadCredentialsException("Email già in uso.");

        Utente utente = new Utente(username, email, passwordEncoder.encode(password));
        utenteRepository.save(utente);
        return getTokenResponse(utente);
    }


    public TokenResponse registerStaff(String username, String email, String password) {
        if (utenteRepository.existsByUsername(username) || staffRepository.existsByUsername(username))
            throw new BadCredentialsException("Username già in uso.");

        if (utenteRepository.existsByEmail(email) || staffRepository.existsByEmail(email))
            throw new BadCredentialsException("Email già in uso.");

        Staff staff = new Staff(username, email, passwordEncoder.encode(password));
        staffRepository.save(staff);
        return getTokenResponse(staff);
    }

    private TokenResponse getTokenResponse(Autenticabile account) {
        User user = new User(
                account.getUsername(),
                account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(account.getRuolo().toAuthority()))
        );
        Map<String, Object> claims = new HashMap<>();

        String token = tokenProvider.generateToken(claims, user);
        return new TokenResponse(token, "Bearer", String.valueOf(tokenProvider.getExpirationTime()));
    }
}