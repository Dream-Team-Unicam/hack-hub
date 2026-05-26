package unicam.dreamteam.domain.service.hackathon.sottomissione;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.sottomissione.Sottomissione;
import unicam.dreamteam.domain.repository.SottomissioneRepository;

@Service
@AllArgsConstructor
public class SottomissioneService {

    private SottomissioneRepository sottomissioneRepository;

    public Sottomissione save(Sottomissione sottomissione) {
        return this.sottomissioneRepository.save(sottomissione);
    }
}
