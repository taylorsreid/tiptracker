package com.tiptracker.api.shift;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.JWT;
import io.github.cdimascio.dotenv.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/api/shift")
public class ShiftController {

    @Autowired
    private ShiftRepository sr;

    Dotenv env = Dotenv.load();

    Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(env.get("PRIVATE_KEY")));
    JWTVerifier verifier = JWT.require(algorithm).withIssuer(env.get("KEY_ISSUER")).build();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public String addNewShift (@RequestBody ShiftRequest shiftRequest) {

//        Shift shiftToAdd = new Shift();
//        BeanUtils.copyProperties(passedShift, shiftToAdd);
//        sr.save(shiftToAdd);
        return "Saved";
    }

//    @GetMapping
//    public @ResponseBody

}