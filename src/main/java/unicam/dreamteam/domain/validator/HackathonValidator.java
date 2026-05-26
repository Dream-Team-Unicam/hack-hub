package unicam.dreamteam.domain.validator;

import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.exception.hackathon.HackathonException;
import unicam.dreamteam.domain.model.Hackathon;
import unicam.dreamteam.domain.model.Team;
import unicam.dreamteam.domain.model.users.Staff;

@Component
public class HackathonValidator {
    public void validTeamIscrittoHackathon(Hackathon hackathon, Team team) {
        if (hackathon.getTeamIscritti().contains(team)) return;
        throw new HackathonException("Il team non è iscritto a questo hackathon");
    }

    public void validaTeamHaInvitoSottomissione(Hackathon hackathon, Team team) {
        if (!hackathon.hasSottomissione(team)) throw new HackathonException(
                String.format(
                        "Il team(id=%s, nome=%s) non ha ancora inviato una sottomissione. (Hackathon.id=%s, Hackathon.nome=%s)",
                        team.getId(),team.getNome(),
                        hackathon.getId(), hackathon.getNome()
                )
        );
    }

    public void validaTeamNonHaInvitoSottomissione(Hackathon hackathon, Team team) {
        if (hackathon.hasSottomissione(team)) throw new HackathonException(
                String.format(
                        "Il team(id=%s, nome=%s) ha già inviato una sottomissione. (Hackathon.id=%s, Hackathon.nome=%s)",
                        team.getId(),team.getNome(),
                        hackathon.getId(), hackathon.getNome()
                )
        );
    }

    public void validaHackathonNonHaMentore(Hackathon hackathon, Staff mentore) {
        if (!hackathon.getMentori().contains(mentore)) return;

        throw new HackathonException(
                String.format(
                        "Mentore è già assegnato all'Hackathon(id=%s, nome=%s)",
                        hackathon.getId(),
                        hackathon.getNome()
                )
        );
    }

    public void validaHackathonHaMentore(Hackathon hackathon, Staff mentore) {
        if (hackathon.getMentori().contains(mentore)) return;

        throw new HackathonException(
                String.format(
                        "Mentore è già assegnato all'Hackathon(id=%s, nome=%s)",
                        hackathon.getId(),
                        hackathon.getNome()
                )
        );
    }
}
