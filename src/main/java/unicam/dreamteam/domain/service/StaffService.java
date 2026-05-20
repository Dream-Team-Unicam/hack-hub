package unicam.dreamteam.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.infrastructure.repository.StaffRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StaffService {
    private StaffRepository staffRepository;

    public List<Staff> getAll() {
        return this.staffRepository.findAll();
    }

    public Optional<Staff> getByUsername(String username) {
        return this.staffRepository.findByUsername(username);
    }
}
