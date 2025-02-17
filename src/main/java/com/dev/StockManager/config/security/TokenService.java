package com.dev.StockManager.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dev.StockManager.entities.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${config.security.token}")
    private String secret;

    public String tokenGenerate(Client client) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String tokenGenerated = JWT.create()
                    .withIssuer("StockManager")
                    .withSubject(client.getName())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            return tokenGenerated;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToke(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("StockManager")
                    .build()
                    .verify(token)
                    .getSubject();


        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
