package fr.formation.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.RandomStringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    // private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";
    private static final String KEY = RandomStringUtils.randomAlphanumeric(64);

    private JwtUtil() { }

    public static String generate() {
        // Création de la clé de signature
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
            .setSubject("username")
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + 36000000))
            .claim("info1", "information 1")
            .claim("info2", "information 2")
            .signWith(key)
            .compact();
    }

    public static boolean isValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
            
            System.out.println(claims.get("info1", String.class));

            return true;
        }

        catch (Exception ex) {
            return false;
        }
    }
}
