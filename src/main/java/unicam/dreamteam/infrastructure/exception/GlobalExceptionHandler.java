package unicam.dreamteam.infrastructure.exception;

import jakarta.persistence.EntityNotFoundException;
import unicam.dreamteam.domain.exception.team.TeamNameAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unicam.dreamteam.domain.exception.team.UserNotInATeamException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TeamNameAlreadyExistsException.class)
    public ResponseEntity<Response> handleTeamNameAlreadyExistsException(TeamNameAlreadyExistsException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        Response error = new Response(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Response error = new Response(
                status.value(),
                status.getReasonPhrase(),
                String.format(
                        "Entità non trovata. %s",
                        exception.getMessage()
                ),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(UserNotInATeamException.class)
    public ResponseEntity<Response> handleUserNotInATeamException(UserNotInATeamException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Response error = new Response(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(status)
                .body(error);
    }
}
