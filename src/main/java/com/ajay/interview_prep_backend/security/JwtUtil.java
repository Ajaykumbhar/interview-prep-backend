package com.ajay.interview_prep_backend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtUtil(@Value("${jwt.secret}")  String secretKey, @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.expiration = expiration;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }



    public boolean isTokenExpired(String token) {
        try{
            Date expiry = extractClaims(token).getExpiration();
            return expiry==null || expiry.before(new Date());
        } catch(JwtException | IllegalArgumentException e){
            return true;
        }
    }

    public boolean validateToken(String token) {
        try{
            extractClaims(token);
            return !isTokenExpired(token);
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
