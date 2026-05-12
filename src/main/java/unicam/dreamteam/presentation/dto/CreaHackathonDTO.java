package unicam.dreamteam.presentation.dto;

import java.time.LocalDate;

public record CreaHackathonDTO(
        String nome,
        String descrizione,
        String regolamento,
        LocalDate dataInizio,
        LocalDate dataFine,
        LocalDate dataScadenzaIscrizioni,
        String luogo,
        Double premioDenaro,
        Integer dimMaxTeam,
        Long organizzatoreId,
        Long giudiceId
) {}
