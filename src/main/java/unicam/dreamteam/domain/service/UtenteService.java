package unicam.dreamteam.domain.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.Invito;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.repository.UtenteRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

    public Optional<Utente> getByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public Utente getById(Long id) {
        Optional<Utente> utente = utenteRepository.findById(id);

        if(utente.isEmpty()) throw new EntityNotFoundException(
                String.format(
                        "Utente.id=%s",
                        id
                )
        );

        return utente.get();
    }
}
