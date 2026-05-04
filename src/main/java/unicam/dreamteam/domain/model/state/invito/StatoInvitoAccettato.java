package unicam.dreamteam.domain.model.state.invito;

public class StatoInvitoAccettato implements StatoInvito {

    @Override
    public StatoInvito accetta() {
        throw new IllegalStateException("L'invito è già stato accettato");
    }

    @Override
    public StatoInvito rifiuta() {
        throw new IllegalStateException("Non puoi rifiutare un invito già accettato");
    }

    @Override
    public String getNome() { return "ACCETTATO"; }
}
