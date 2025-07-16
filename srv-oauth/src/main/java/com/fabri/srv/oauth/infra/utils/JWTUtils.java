package com.fabri.srv.oauth.infra.utils;

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
import java.util.Map;

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
        JwtParser build = Jwts.parser().verifyWith(key).build();
        return (Claims) build.parse(token).getPayload();
    }

    public Instant getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.toInstant();
    }

    public String generateToken(String userId, String userRoles, JWTType tokenType) {
        long expMilis = JWTType.ACESS_TOKEN.equals(tokenType) ? expiration * 1000 : expiration * 1000 * 5;

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expMilis);

        final var claims = Map.of("id", userId, "roles", userRoles);
        return Jwts.builder().claims(claims).issuedAt(now).expiration(expirationDate).signWith(key).compact();
    }
}
