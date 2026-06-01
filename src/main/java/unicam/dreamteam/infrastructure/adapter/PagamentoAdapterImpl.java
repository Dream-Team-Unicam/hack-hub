package unicam.dreamteam.infrastructure.adapter;

import unicam.dreamteam.domain.adapter.PagamentoAdapter;

public class PagamentoAdapterImpl implements PagamentoAdapter {

    @Override
    public void pagaPremio(Long hackathonId, Long teamId, double importo) {
        throw new RuntimeException("Non implementato");
    }
}
