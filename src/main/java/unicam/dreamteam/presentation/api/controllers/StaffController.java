package unicam.dreamteam.presentation.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.mapper.AccountMapper;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
@PreAuthorize("isAuthenticated()")
public class StaffController {
    private StaffService staffService;
    private AccountMapper accountMapper;

    @GetMapping("/admins")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllAdmins() {
        throw new RuntimeException("non implementato");
    }

    @GetMapping("/giudici")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllGiudici() {
        throw new RuntimeException("non implementato");
    }

    @GetMapping("/organizzatori")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllOrganizzatori() {
        throw new RuntimeException("non implementato");
    }

    @GetMapping("/mentori")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllMentori() {
        throw new RuntimeException("non implementato");
    }

}
