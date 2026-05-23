package unicam.dreamteam.presentation.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.mapper.AccountMapper;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.AllArgsConstructor;


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
                .toList();
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllAdmins() {
        return this.staffService.getAllByRuolo(RuoloStaff.ADMIN)
                .stream()
                .map(accountMapper::toResponse)
                .toList();

    }

    @GetMapping("/giudici")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllGiudici() {
        return this.staffService.getAllByRuolo(RuoloStaff.GIUDICE)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @GetMapping("/giudici/hackathons")
    @PreAuthorize("hasAnyRole('ADMIN', 'GIUDICE')")
    public List<AccountResponse> getAllGiudiceHackthons(
            @RequestBody Long idGiudice, Authentication authentication
    ) {
        Staff currentUser = this.staffService.getByUsername(authentication.getName());
        if (currentUser.getRuolo() == RuoloStaff.ADMIN) {

        }

        return this.staffService.getAllByRuolo(RuoloStaff.GIUDICE)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @GetMapping("/organizzatori")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<AccountResponse> getAllOrganizzatori() {
        return this.staffService.getAllByRuolo(RuoloStaff.ORGANIZZATORE)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }



    @GetMapping("/mentori")
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<AccountResponse> getAllMentori() {
        return this.staffService.getAllByRuolo(RuoloStaff.MENTORE)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

}
