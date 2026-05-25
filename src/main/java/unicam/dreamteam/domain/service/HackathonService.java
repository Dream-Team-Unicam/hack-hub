package unicam.dreamteam.domain.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.builder.HackathonBuilder;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.validator.RuoloValidator;
import unicam.dreamteam.infrastructure.repository.HackathonRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import unicam.dreamteam.presentation.dto.hackathon.HackathonDTO;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    private final RuoloValidator ruoloValidator;

    public Hackathon getById(Long id) {
        Optional<Hackathon> hackathon = this.hackathonRepository.findById(id);

        if (hackathon.isEmpty()) throw new EntityNotFoundException(
                String.format(
                        "Hackathon.id=%s",
                        id
                )
        );

        return hackathon.get();
    }

    public List<Hackathon> getAllByGiudice(Staff giudice) {
        this.ruoloValidator.validaGiudice(giudice.getRuolo());

        return this.hackathonRepository.findAllByGiudiceId(giudice.getId());
    }

    public Hackathon save(HackathonDTO request, Staff organizzatore, Staff giudice) {
        this.ruoloValidator.validaGiudice(giudice.getRuolo());

        Hackathon newHackathon = new HackathonBuilder()
                .nome(request.getNome())
                .descrizione(request.getDescrizione())
                .regolamento(request.getRegolamento())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .dataScadenzaIscrizioni(request.getDataScadenzaIscrizioni())
                .premioDenaro(request.getPremioDenaro())
                .luogo(request.getLuogo())
                .dimMaxTeam(request.getDimMaxTeam())
                .organizzatore(organizzatore)
                .giudice(giudice)
                .build();

        return this.hackathonRepository.save(newHackathon);
    }

    public Hackathon aggiungi(Long hackathonId, Staff mentore) {
        this.ruoloValidator.validaMentore(mentore.getRuolo());

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new EntityNotFoundException("Hackathon.id=" + hackathonId));

        if (hackathon.getMentori().contains(mentore)) throw new ValidationException(
                "Mentore già nell'Hackathon"
        );

        hackathon.aggiungiMentore(mentore);
        hackathonRepository.save(hackathon);

        // ricarica con tutti i dettagli
        return hackathonRepository.findById(hackathonId)
                .orElseThrow();
    }

    public Hackathon rimuovi(Long hackathonId, Staff mentore) {
        this.ruoloValidator.validaMentore(mentore.getRuolo());

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new EntityNotFoundException("Hackathon.id=" + hackathonId));


        hackathon.aggiungiMentore(mentore);
        hackathonRepository.save(hackathon);

        // ricarica con tutti i dettagli
        return hackathonRepository.findById(hackathonId)
                .orElseThrow();
    }

    public List<Hackathon> getAllHackathons() {
        return hackathonRepository.findAll();
    }


}
