package unicam.dreamteam.infrastructure.config;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.ruolo.RuoloStaff;
import unicam.dreamteam.infrastructure.repository.StaffRepository;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO: Rimuovere. Usare solo a scopo di sviluppo.
    @Override
    public void run(@NonNull ApplicationArguments args) {
        if (staffRepository.existsByUsername("admin")) return;

        Staff admin = new Staff(
                "admin",
                "admin@hack-hub.it",
                passwordEncoder.encode("admin"),
                RuoloStaff.ADMIN
        );

        staffRepository.save(admin);
        System.out.println("Admin creato: admin/admin");
    }
}
