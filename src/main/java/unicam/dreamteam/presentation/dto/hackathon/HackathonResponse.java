package unicam.dreamteam.presentation.dto.hackathon;

import java.time.LocalDate;

public record HackathonResponse(
        Long id,
        String nome,
        String descrizione,
        String regolamento,
        LocalDate dataInizio,
        LocalDate dataFine,
        LocalDate dataScadenzaIscrizioni,
        String luogo,
        Double premioDenaro,
        Integer dimMaxTeam,
        String stato,
        Long organizzatoreId,
        Long giudiceId,
        Long teamVincitoreId
) {}
