package choplan.db.application.properties.choplan.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {
    
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long validityInMs = 1000 * 60 * 60;

    // 토큰 생성
    public String createToken(String email, String role) {
        return Jwts.builder()
        .setSubject(email)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
        .signWith(secretKey)
        .compact();
    }
}

// 토큰에서 사용자 이메일 추출
public String getEmail(String token) {
    return Jwts.parserBuilder()
    .setSigningKey(secretKey)
    .build()
    .parseClaimsJws(token)
    .getBody()
    .getSubject();
}

// 토큰 유효성 검사
public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder().setSigningKet(secretKey).build().parseClaimsJws(token);
        return true;
    } catch (JwtException | IllegalArgumentExcption e) {
        return false;
    }
}
