package unicam.dreamteam.presentation.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.mapper.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/staffs")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class StaffController {
    private StaffService staffService;
    private AccountMapper accountMapper;


    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAll() {
        return this.staffService.getAll()
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllAdmins() {
        return this.staffService.getByRuolo(RuoloStaff.ADMIN)
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());

    }

    @GetMapping("/giudici")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllGiudici() {
        return this.staffService.getByRuolo(RuoloStaff.GIUDICE)
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/organizzatori")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllOrganizzatori() {
        return this.staffService.getByRuolo(RuoloStaff.ORGANIZZATORE)
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/mentori")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllMentori() {
        return this.staffService.getByRuolo(RuoloStaff.MENTORE)
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

}
