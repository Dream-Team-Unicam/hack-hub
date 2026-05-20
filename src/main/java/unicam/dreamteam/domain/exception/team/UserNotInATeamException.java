package unicam.dreamteam.domain.exception.team;

public class UserNotInATeamException extends RuntimeException {
    public UserNotInATeamException(String info) {
        super(String.format(
                "L'utente non è in un team. %s",
                info
        ));
    }
}
