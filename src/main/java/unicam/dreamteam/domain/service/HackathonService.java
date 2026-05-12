package unicam.dreamteam.domain.service;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.repository.HackathonRepository;
import unicam.dreamteam.presentation.dto.HackathonDTO;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;

@Service
@AllArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;


    public List<HackathonDTO> getAllHackathons() {
        return hackathonRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public HackathonDTO toDTO(Hackathon obj) {
        if (obj == null) throw new IllegalArgumentException("Hackathon non può essere null.");
        return new HackathonDTO(
                obj.getId(),
                obj.getNome(),
                obj.getDescrizione(),
                obj.getRegolamento(),
                obj.getDataInizio(),
                obj.getDataFine(),
                obj.getDataScadenzaIscrizioni(),
                obj.getLuogo(),
                obj.getPremioDenaro(),
                obj.getDimMaxTeam(),
                obj.getStato().getNome(),
                obj.getOrganizzatore() != null ? obj.getOrganizzatore().getId() : null,
                obj.getGiudice() != null ? obj.getGiudice().getId() : null,
                obj.getTeamVincitore() != null ? obj.getTeamVincitore().getId() : null
        );
    }

    public Hackathon toObject(HackathonDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Dati Hackathon non validi.");
        // TODO: Da finire
        return new Hackathon();
    }
}
