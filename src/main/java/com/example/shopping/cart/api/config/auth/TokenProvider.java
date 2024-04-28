package com.example.shopping.cart.api.config.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.shopping.cart.api.model.User;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String JWT_SECRET;

    public TokenProvider() {
        System.out.println(JWT_SECRET);
    }

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

            return JWT.create().withSubject(user.getUsername()).withClaim("username", user.getUsername())
                    .withExpiresAt(genAccessExpirationDate()).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (Exception e) {
            throw new JWTVerificationException("Error while verifying token", e);
        }
    }

    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
