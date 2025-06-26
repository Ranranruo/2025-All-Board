package sms.allBoard.Common.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final int ACCESS_TIMEOUT = 1000 * 60 * 10;
    private final int REFRESH_TIMEOUT = 1000 * 60 * 60 * 24 * 7;
    private final SecretKey secret;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        this.secret = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return this.getClaims(token).getExpiration().before(new Date());
    }


    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.ACCESS_TIMEOUT))
                .signWith(this.secret, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.REFRESH_TIMEOUT))
                .signWith(this.secret, SignatureAlgorithm.HS256)
                .compact();
    }
}