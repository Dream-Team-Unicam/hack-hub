package unicam.dreamteam.domain.service;

import unicam.dreamteam.domain.repository.HackathonRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import unicam.dreamteam.presentation.dto.HackathonDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;

    public List<HackathonDTO> getAllHackathons() {
        return null;
    }
}
