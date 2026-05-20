package unicam.dreamteam.domain.exception.team;

public class TeamNameAlreadyExistsException extends RuntimeException {
    public TeamNameAlreadyExistsException(String nome) {
        super(String.format(
                "Esiste già un team con questo nome: %s",
                nome
        ));
    }
}
