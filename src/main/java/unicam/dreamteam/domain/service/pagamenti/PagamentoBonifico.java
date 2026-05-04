package unicam.dreamteam.domain.service.pagamenti;

public class PagamentoBonifico implements MetodoPagamento {
    private String iban;

    public PagamentoBonifico(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean paga(Double importo) {
        throw new RuntimeException("Metodo non implementato");
    }
}
