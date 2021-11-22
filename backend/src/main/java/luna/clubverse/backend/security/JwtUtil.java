package luna.clubverse.backend.security;

/**
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class JwtUtil {

    public static String generateToken(Authentication user, String key) {
        return Jwts.builder()
                .setSubject(user.getName())
                .claim("authorities", getAuthorities(user))
                .setExpiration(getExpirationDate())
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    private static List<String> getAuthorities(Authentication user) {

        return user.getAuthorities()
                .stream()
                .map(authority ->  (authority.getAuthority() ))
                .toList();
    }

    private static Date getExpirationDate() {
        Instant expirationTime = LocalDate.now()
                .plusDays(7)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Date.from(expirationTime);
    }

    public static String extractUsername(String jwtToken, String key) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        return claims.getSubject();
    }
}
 */
