package unicam.dreamteam.presentation.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.dreamteam.domain.service.StaffService;
import unicam.dreamteam.presentation.dto.staff.response.StaffResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/staffs")
@PreAuthorize("isAuthenticated()")
public class StaffController {

    private StaffService staffService;

    public List<StaffResponse> getAllStaffs() {
        //List<StaffResponse> staffs = this.staffService.getAll().stream().map();

        return new ArrayList<>();
    }
}
