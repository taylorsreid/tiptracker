package com.tiptracker.api.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class AuthorizationService {

    //token configuration
    private final String ISSUER = "tiptracker"; //issuer name
    private final String ENV_VAR = "HMAC256"; //name of secret key stored in system environment variables

    //algorithm and verifier
    private final Algorithm algorithm = Algorithm.HMAC256(System.getenv(ENV_VAR));
    private final JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();

    /**
     * Generates a token for the passed UUID of the user.  Does NOT perform verification or validation.
     * @param userId the UUID of the user to get a token for
     * @return a new JWT for the passed user argument
     * @throws JWTCreationException if the token can't be created
     */
    public String getTokenForUserId(String userId) throws JWTCreationException{

        final int SECONDS_TO_ADD = 7200; //7200 seconds is two hours

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(userId)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(SECONDS_TO_ADD))
                .sign(algorithm);
    }

    /**
     * Verifies the token contained in the authorization header.
     * @param authorizationHeader the authorization header passed.
     * @return boolean true if the token is valid, false if it is not
     */
    public boolean verifyAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader.startsWith("Bearer ")) {
            try {
                verifier.verify(authorizationHeader.substring(7));
                return true;
            }
            catch (JWTVerificationException e){
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Extracts the UUID from the authorization header.
     * @param authorizationHeader the authorization header passed.
     * @return the UUID contained in the token as a string.
     */
    public String getUserIdFromAuthorizationHeader(String authorizationHeader){
        return verifier.verify(authorizationHeader.substring(7)).getSubject();
    }

}