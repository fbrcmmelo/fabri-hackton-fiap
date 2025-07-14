package com.fabri.srv.oauth.infra.utils;

import com.fabri.srv.oauth.domain.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    private SecretKey key;

    @PostConstruct
    public void iniKey() {
        this.key = Keys.hmacShaKeyFor(this.secret.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().decryptWith(key).build().parseUnsecuredClaims(token).getPayload();
    }

    public Instant getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.toInstant();
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).isBefore(Instant.now());
    }

    public String generateToken(String userId, Set<Role> userRoles, JWTType tokenType) {
        long expMilis = JWTType.ACESS_TOKEN.equals(tokenType) ? expiration * 1000 : expiration * 1000 * 5;

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expMilis);

        final var claims = Map.of("id", userId, "role", userRoles);
        return Jwts.builder().claims(claims).issuedAt(now).expiration(expirationDate).signWith(key).compact();
    }
}
