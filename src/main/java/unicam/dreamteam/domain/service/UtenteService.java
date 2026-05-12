package unicam.dreamteam.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.repository.UtenteRepository;


@Service
@AllArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }
}
