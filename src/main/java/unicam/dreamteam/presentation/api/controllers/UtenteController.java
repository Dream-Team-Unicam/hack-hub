package unicam.dreamteam.presentation.api.controllers;

import unicam.dreamteam.domain.model.state.invito.StatoInvitoPendente;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.domain.service.accounts.UtenteService;
import unicam.dreamteam.domain.service.team.InvitoService;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.dto.team.response.InvitoResponse;
import unicam.dreamteam.presentation.mapper.AccountMapper;
import unicam.dreamteam.presentation.mapper.InvitoMapper;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utente")
@PreAuthorize("hasRole('UTENTE')")
@AllArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;
    private final InvitoService invitoService;
    private final AccountMapper accountMapper;
    private final InvitoMapper invitoMapper;

    @GetMapping()
    public ResponseEntity<AccountResponse> index(Authentication authentication) {
        Utente utente = utenteService.getByUsername(authentication.getName());
        return ResponseEntity.ok(this.accountMapper.toResponse(utente));
    }

    @GetMapping("/inviti")
    public ResponseEntity<List<InvitoResponse>> getAllInviti(Authentication authentication) {
        Utente utente = utenteService.getByUsername(authentication.getName());
        return ResponseEntity.ok(
                this.invitoService.getAllByUtenteAndStato(utente, new StatoInvitoPendente()).stream()
                        .map(this.invitoMapper::toResponse)
                        .toList());
    }
}
