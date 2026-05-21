package unicam.dreamteam.domain.service.security.userdetails;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unicam.dreamteam.domain.model.users.Staff;
import unicam.dreamteam.domain.model.users.Utente;
import unicam.dreamteam.infrastructure.repository.StaffRepository;
import unicam.dreamteam.infrastructure.repository.UtenteRepository;
import unicam.dreamteam.domain.service.security.Autenticabile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UtenteRepository utenteRepository;
    private final StaffRepository staffRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> utente = utenteRepository.findByUsername(username);
        if (utente.isPresent()) return buildUserDetails(utente.get());

        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff.isPresent()) return buildUserDetails(staff.get());

        throw new UsernameNotFoundException("Utente non trovato: " + username);
    }

    private UserDetails buildUserDetails(Autenticabile account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRuolo().toAuthority()));
        System.out.println("Authorities per " + account.getUsername() + ": " + authorities);
        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}