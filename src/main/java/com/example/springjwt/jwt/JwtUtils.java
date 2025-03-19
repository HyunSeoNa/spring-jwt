package com.example.springjwt.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * fileName     : null.java
 * author       : hyunseo
 * date         : 2025. 3. 20.
 * description  :
 */
@Component
public class JwtUtils {

    private SecretKey secretKey;

    public JwtUtils(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 검증
    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().getExpiration().before(new Date());
    }

    // Token 생성
    public String createJwt(String userName, String role, Long expiredTime) {
        return Jwts.builder()
                .claim("username", userName)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(secretKey)
                .compact();
    }

}
