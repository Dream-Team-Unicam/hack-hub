package unicam.dreamteam.presentation.mapper;

import org.springframework.stereotype.Component;
import unicam.dreamteam.domain.service.security.Autenticabile;
import unicam.dreamteam.presentation.dto.security.response.AccountResponse;

@Component
public class AccountMapper implements IMapper<Autenticabile, AccountResponse> {
    @Override
    public AccountResponse toResponse(Autenticabile autenticabile) {
        return new AccountResponse(
                autenticabile.getId(),
                autenticabile.getUsername(),
                autenticabile.getEmail(),
                autenticabile.getRuolo().getName()
        );
    }
}
