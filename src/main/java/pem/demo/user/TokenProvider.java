package pem.demo.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;


@Component
public class TokenProvider {
    private static final String CLAIM_KEY = "user";

    @Value("${jwt.base64-secret}")
    private String base64SecretKey;
    @Value("${jwt.token-validity-in-minutes}")
    private Long tokenValidityInMinutes;

    public String makeJwt(String payLoad) {
        Date now = new Date();
        System.out.println("tokenValidityInMinutes = " + tokenValidityInMinutes);
        Date validity = new Date(now.getTime() + Duration.ofMinutes(tokenValidityInMinutes).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("changJun")
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim(CLAIM_KEY, payLoad)
                .signWith(SignatureAlgorithm.HS256, base64SecretKey)
                .compact();
    }

    public Claims parseJwtToken(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);

        return Jwts.parser()
                .setSigningKey(base64SecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException("정상적인 토큰이 아닙니다.");
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}
