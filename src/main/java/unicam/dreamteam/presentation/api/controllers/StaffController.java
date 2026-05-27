package unicam.dreamteam.presentation.api.controllers;

import org.springframework.security.core.Authentication;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.domain.service.facade.HackathonFacade;
import unicam.dreamteam.domain.service.hackathon.HackathonService;
import unicam.dreamteam.domain.service.accounts.StaffService;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;
import unicam.dreamteam.presentation.mapper.AccountMapper;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.AllArgsConstructor;
import unicam.dreamteam.presentation.mapper.HackathonMapper;


@RestController
@RequestMapping("/api/staffs")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class StaffController {
    private HackathonFacade hackathonFacade;

    private StaffService staffService;
    private AccountMapper accountMapper;
    private HackathonMapper hackathonMapper;


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

    @GetMapping("/hackathons")
    @PreAuthorize("hasAnyRole('GIUDICE', 'ORGANIZZATORE', 'MENTORE')")
    public List<HackathonDTO> getAllStaffHackathons(Authentication authentication) {
        return this.hackathonFacade.listaHackathonByUsername(authentication.getName()).stream()
                .map(hackathonMapper::toResponse)
                .toList();
    }
}
