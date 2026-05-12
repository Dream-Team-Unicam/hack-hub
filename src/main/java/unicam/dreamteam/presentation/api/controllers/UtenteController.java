package unicam.dreamteam.presentation.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utenti")
@PreAuthorize("hasRole('UTENTE')")
@AllArgsConstructor
public class UtenteController {
}
