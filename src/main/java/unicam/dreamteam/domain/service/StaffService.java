package unicam.dreamteam.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.repository.StaffRepository;

@Service
@AllArgsConstructor
public class StaffService {
    private StaffRepository staffRepository;
}
