package com.fabri.srv.api.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;
    private SecretKey key;

    @PostConstruct
    public void iniKey() {
        this.key = Keys.hmacShaKeyFor(this.secret.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        JwtParser build = Jwts.parser().verifyWith(key).build();
        return (Claims) build.parse(token).getPayload();
    }

    private Instant getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();

        return expirationDate.toInstant();
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).isBefore(Instant.now());
    }

    public boolean hasRole(String token, RoleEnum role) {
        if (token == null || token.isBlank()) return false;

        Claims claims = getClaimsFromToken(token);
        String roles = Optional.ofNullable(claims.get("roles", String.class)).orElse("");

        return roles.equalsIgnoreCase(role.name());
    }
}
