package sms.allBoard.Auth.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

public interface JwtService {
    String getUsername(String token);
    boolean isExpired(String token);
    String generateAccessToken(String username);
    String generateRefreshToken(String username);
}