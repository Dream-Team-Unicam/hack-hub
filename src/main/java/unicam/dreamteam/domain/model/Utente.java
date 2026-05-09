package unicam.dreamteam.domain.model;

import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "utenti")
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final Set<Invito> inviti = new HashSet<>();

    public Utente(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public boolean hasTeam() {
        return this.team != null;
    }

    public Set<Invito> getInviti() {
        return inviti;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Utente utente
                && Objects.equals(id, utente.id)
                && Objects.equals(username, utente.username)
                && Objects.equals(email, utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
