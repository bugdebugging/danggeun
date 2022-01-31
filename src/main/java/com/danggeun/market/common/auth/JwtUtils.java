package com.danggeun.market.common.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expired}")
    private Long expired;

    public String publishTokenTo(String username) {
        Date expiredAt = Timestamp.valueOf(LocalDateTime.now().plusSeconds(expired));
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withExpiresAt(expiredAt)
                .withIssuer(issuer)
                .withClaim("username",username)
                .sign(algorithm);
    }

    public String verifyTokenIsValid(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("username").asString();
    }
}
