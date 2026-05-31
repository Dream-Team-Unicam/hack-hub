package unicam.dreamteam.domain.model.state.invito;

public interface StatoInvito {
    StatoInvito accetta();
    StatoInvito rifiuta();
    String getNome();
}
