package unicam.dreamteam.domain.service;

import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.infrastructure.repository.StaffRepository;
import unicam.dreamteam.infrastructure.repository.HackathonRepository;
import unicam.dreamteam.presentation.dto.hackathon.HackathonRequest;
import unicam.dreamteam.presentation.dto.hackathon.HackathonResponse;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    private final StaffRepository staffRepository;

    public Hackathon createHackathon(HackathonRequest request, Staff organizzatore) {

        

        Hackathon newHackathon = new Hackathon(
                request.nome(),
                request.descrizione(),
                request.regolamento(),
                request.dataInizio(),
                request.dataFine(),
                request.dataScadenzaIscrizioni(),
                request.luogo(),
                request.premioDenaro(),
                request.dimMaxTeam(),
                organizzatore,
                organizzatore
        );
        return this.hackathonRepository.save(newHackathon);
    }

    public List<Hackathon> getAllHackathons() {
        return null;
    }


    public HackathonResponse toResponse(Hackathon hackathon) {
        return new HackathonResponse(
                hackathon.getId(),
                hackathon.getNome(),
                hackathon.getDescrizione(),
                hackathon.getRegolamento(),
                hackathon.getDataInizio(),
                hackathon.getDataFine(),
                hackathon.getDataScadenzaIscrizioni(),
                hackathon.getLuogo(),
                hackathon.getPremioDenaro(),
                hackathon.getDimMaxTeam(),
                hackathon.getStato().getNome(),
                hackathon.getOrganizzatore().getId(),
                hackathon.getGiudice().getId(),
                hackathon.getTeamVincitore().getId()
        );
    }
}
