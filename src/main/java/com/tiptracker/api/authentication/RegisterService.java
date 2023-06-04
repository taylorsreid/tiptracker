package com.tiptracker.api.authentication;

import com.tiptracker.api.GenericResponse;
import com.tiptracker.api.user.User;
import com.tiptracker.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> register(RegisterRequest registerRequest){

        //create new blank response
//        RegisterResponse registerResponse = new RegisterResponse();
//        GenericResponse registerResponse = new GenericResponse();

        //check that email doesn't already exist
        boolean availableEmail = !userRepository.existsByEmailAllIgnoreCase(registerRequest.getEmail());

        //validate email is real
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(registerRequest.getEmail());
        boolean validEmail = matcher.find();

        //validate password meets requirements
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(registerRequest.getPassword());
        boolean validPassword = passwordMatcher.find();

        //if password meets requirements and email isn't taken
        if (availableEmail && validEmail && validPassword) {

            //create new user and set required and unhashed values
            User newUser = new User();
            newUser.setEmail(registerRequest.getEmail());
            newUser.setFirstName(registerRequest.getFirstName());
            newUser.setLastName(registerRequest.getLastName());

            //create encoder and encode raw password into a hashed one
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            String hashedPassword = bcpe.encode(registerRequest.getPassword());

            //if hashed password is weak, keep reencoding until it isn't then set it
            while (bcpe.upgradeEncoding(hashedPassword)) {
                hashedPassword = bcpe.encode(registerRequest.getPassword());
            }
            newUser.setHashedPassword(hashedPassword);

            //save to db
            userRepository.save(newUser);

            //registerResponse.setMessage("Account created successfully.");

            //will only return when all 3 booleans are true and account has been created\
            return ResponseEntity.ok(new GenericResponse(true, "Account created successfully.").toString());

        }

        //set failure message and return reasons why
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(false);
        genericResponse.appendBody("Email available: " + availableEmail);
        genericResponse.appendBody(" | Valid email: " + validEmail);
        genericResponse.appendBody(" | Valid password: " + validPassword);

        return ResponseEntity.badRequest().body(genericResponse.toString());

    }

}
