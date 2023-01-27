package com.tiptracker.api.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/register", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestParam String apiVersion,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestBody(required = false) String jobs) {

        //validate password
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = pattern.matcher(password);
        boolean validPassword = matcher.find();

        //check that email doesn't already exist
        boolean availableEmail = !userRepository.existsByEmailAllIgnoreCase(email);

        try{

            //if password meets requirements and email isn't taken
            if (validPassword && availableEmail) {

                //
                User userToAdd = new User();

                //
                userToAdd.setEmail(email);
                userToAdd.setHashedPassword(password);
                userToAdd.setFirstName(firstName);
                userToAdd.setLastName(lastName);
//                userToAdd.setJobs(jobs);



                //save to db
                userRepository.save(userToAdd);

                //create and return JWT token with payload
                return userToAdd.getNewToken();

            }
            else {
                return "{\"availableEmail\": " + availableEmail + "," +
                        "\"validEmail\": true," +
                        "\"validPassword\": " + validPassword + "," +
                        "\"message\": \"Emails must be valid domains and passwords must contain at least a minimum of eight characters, one uppercase letter, one lowercase letter, one number, and one special character\"}";
            }
        }
        catch (org.springframework.transaction.TransactionSystemException ex){
            return "{\"availableEmail\": true," +
                    "\"validEmail\": false," +
                    "\"validPassword\": true," +
                    "\"message\": \"Emails must be valid domains and passwords must contain at least a minimum of eight characters, one uppercase letter, one lowercase letter, one number, and one special character\"}";
        }

    }

    @PostMapping(path = "/login", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestParam String apiVersion,
                        @RequestParam String email,
                        @RequestParam String password) {

        try {

            //get the actual user that the claimant claims to be
            User actualUser = userRepository.findByEmail(email);

            //
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            //if password matches, it's them so return a new token
            if (passwordEncoder.matches(password, actualUser.getHashedPassword())){
                return actualUser.getNewToken();
            }
            else{
                //TODO: FIXME so that I return a proper error
                return valueOf(HttpServletResponse.SC_UNAUTHORIZED);
            }

        }
        catch (Exception e){
            //TODO: FIXME so that I return a proper error
            return valueOf(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}