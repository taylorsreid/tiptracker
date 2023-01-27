package com.tiptracker.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class JWTTest {

    public static void main(String[] args) {

        Dotenv env = Dotenv.load();

        Algorithm algo = Algorithm.HMAC256(Objects.requireNonNull(env.get("PRIVATE_KEY")));

        for (int i = 0; i < 10; i++){
            System.out.println(JWT.create()
                    .withSubject("d6ae539b-83b7-4076-a77f-2f088a3f6ce6")
                    .withIssuer(env.get("KEY_ISSUER"))
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .sign(algo));
        }

    }

}
