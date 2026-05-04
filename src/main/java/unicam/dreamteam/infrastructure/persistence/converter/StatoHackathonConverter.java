package unicam.dreamteam.infrastructure.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import unicam.dreamteam.domain.model.state.hackathon.*;

@Converter(autoApply = true)
public class StatoHackathonConverter implements AttributeConverter<StatoHackathon, String> {
    @Override
    public String convertToDatabaseColumn(StatoHackathon stato) {
        return stato == null ? null : stato.getNome();
    }

    @Override
    public StatoHackathon convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return switch (dbValue) {
            case "CREATO"     -> new StatoHackathonCreato();
            case "ISCRIZIONE"    -> new StatoHackathonIscrizione();
            case "IN_CORSO"    -> new StatoHackathonInCorso();
            case "VALUTAZIONE"    -> new StatoHackathonValutazione();
            case "CONCLUSO" -> new StatoHackathonConcluso();
            default -> throw new IllegalArgumentException("Stato sconosciuto: " + dbValue);
        };
    }
}
