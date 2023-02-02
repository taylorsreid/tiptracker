package com.tiptracker.api.authentication;

import com.tiptracker.api.user.User;
import com.tiptracker.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    public ResponseEntity<String> login(LoginRequest loginRequest){

        //gets the actual user so that they can be compared to the alleged user
        User actualUser = userRepository.findByEmail(loginRequest.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //compare requested password with the actual one's hash
        if (passwordEncoder.matches(loginRequest.getPassword(), actualUser.getHashedPassword())){

            //create AuthorizationService object
            AuthorizationService authorizationService = new AuthorizationService();

            //create token for authorized user
            String token = authorizationService.getTokenForUserId(actualUser.getUserId());

            //create headers to add to bearer token to
            HttpHeaders httpHeaders = new HttpHeaders();

            //add token to headers
            httpHeaders.setBearerAuth(token);

            //return positive response along with JWT bearer token
            return new ResponseEntity<>("{\"success\": true,\n\"message\": \"Successfully logged in.\"}", httpHeaders, 200);

        }
        else {
            //return negative response with 401 code
            return new ResponseEntity<>("{\"success\": false,\n\"message\": \"Incorrect email or password!\"}", HttpStatus.UNAUTHORIZED);
        }

    }

}
