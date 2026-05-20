package unicam.dreamteam.domain.exception;

public class InvitoNotValidException extends RuntimeException {
    public InvitoNotValidException(String reason) {
        super(
                String.format(
                        "Invito non valido per motivo: %s",
                        reason
                )
        );
    }
}
