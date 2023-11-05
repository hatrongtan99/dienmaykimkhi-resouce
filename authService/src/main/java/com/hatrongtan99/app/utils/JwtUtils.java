package com.hatrongtan99.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private Key key;

    public JwtUtils(@Value("${hatrongtan99.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private final Long EXPIRED_TIME = (long) (60 * 1000 * 30);


    public String generateToken(Long id, Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(this.key)
                .subject(id.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.EXPIRED_TIME))
                .claims(claims)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String usernameFromToken = this.extractClaims(token, claims -> (String) claims.get("username"));
        if (username == null) {
            return false;
        }
        return usernameFromToken.equals(username)
                && this.extractClaims(token, Claims::getExpiration).after(new Date());
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = this.extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public Long getUserId(String token) {
        return Long.valueOf(this.extractClaims(token, Claims::getSubject));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) this.key)
                .build()
                .parseSignedClaims(token).getPayload();
    }

}
