package unicam.dreamteam.domain.service.security.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String usename = getUsernameFromToken(token);
        return (usename.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        String ruolo = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)
                .filter(a -> a.startsWith("ROLE_"))
                .map(a -> a.replace("ROLE_", ""))
                .findFirst()
                .orElse(null);

        claims.put("ruolo", ruolo);

        return generateToken(
                claims,
                userDetails,
                new Date(System.currentTimeMillis() + (expiration * 1000))
        );
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails, Date expiration) {
        return Jwts.builder()
                .claims().add(claims).and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(expiration)
                .signWith(key())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    private Claims getAllClaimsFromToken(String token) {
       return Jwts.parser()
               .verifyWith(key())
               .build()
               .parseSignedClaims(token)
               .getPayload();

    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> resolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return resolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public long getExpirationTime() {
        return this.expiration;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }


}
