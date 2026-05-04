package unicam.dreamteam.domain.model.state.invito;

public class StatoInvitoRifiutato implements StatoInvito {

    @Override
    public StatoInvito accetta() {
        throw new IllegalStateException("Non puoi accettare un invito rifiutato");
    }

    @Override
    public StatoInvito rifiuta() {
        throw new IllegalStateException("L'invito è già stato rifiutato");
    }

    @Override
    public String getNome() { return "RIFIUTATO"; }
}
