package unicam.dreamteam.presentation.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;

@Component
@AllArgsConstructor
public class HackathonMapper implements IMapper<Hackathon, HackathonDTO> {
    private TeamMapper teamMapper;
    private AccountMapper accountMapper;

    @Override
    public HackathonDTO toResponse(Hackathon hackathon) {
        return new HackathonDTO(
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
                hackathon.getOrganizzatore() == null ? null : this.accountMapper.toResponse(hackathon.getOrganizzatore()),
                hackathon.getGiudice() == null ? null : this.accountMapper.toResponse(hackathon.getGiudice()),
                hackathon.getMentori().stream()
                        .map(this.accountMapper::toResponse)
                        .toList(),
                hackathon.getTeamIscritti().stream()
                        .map(this.teamMapper::toResponse)
                        .toList(),
                hackathon.getTeamVincitore() == null ? null : teamMapper.toResponse(hackathon.getTeamVincitore())
        );
    }
}
