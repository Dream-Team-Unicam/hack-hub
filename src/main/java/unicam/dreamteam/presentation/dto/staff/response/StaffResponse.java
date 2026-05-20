package unicam.dreamteam.presentation.dto.staff.response;

import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;

public record StaffResponse  (
        Long id,
        String username,
        String email,
        String password,
        RuoloStaff ruolo
) {

}
