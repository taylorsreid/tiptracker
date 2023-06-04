package com.tiptracker.api;


import com.tiptracker.api.authentication.*;
import com.tiptracker.api.shift.GetShiftRequest;
import com.tiptracker.api.shift.PostShiftRequest;
import com.tiptracker.api.shift.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

//    @Autowired
//    private AuthorizationService authorizationService;
//
//    @Autowired
//    private RegisterService registerService;
//    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
//        return registerService.register(registerRequest);
//    }
//
//    @Autowired
//    private LoginService loginService;
//    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
//        return loginService.login(loginRequest);
//    }

    @Autowired
    private ShiftService shiftService;
    @PostMapping(path = "/shift", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> createShifts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody List<PostShiftRequest> shiftRequestList){
//        if(authorizationService.verifyAuthorizationHeader(authorizationHeader)){
//            return shiftService.createShifts(authorizationService.getUserIdFromAuthorizationHeader(authorizationHeader), shiftRequestList);
        return null;
//        }
//        else{
//            return new ResponseEntity<>(new GenericResponse(false, "BAD TOKEN").toString(), HttpStatus.UNAUTHORIZED);
//        }
    }
    @GetMapping(path = "/shift", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> getShifts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody GetShiftRequest getShiftRequest){
//        if(authorizationService.verifyAuthorizationHeader(authorizationHeader)){
//            return shiftService.getShifts(authorizationService.getUserIdFromAuthorizationHeader(authorizationHeader), getShiftRequest);
        return null;
//        }
//        else{
//            return new ResponseEntity<>(new GenericResponse(false, "BAD TOKEN").toString(), HttpStatus.UNAUTHORIZED);
//        }
    }

}