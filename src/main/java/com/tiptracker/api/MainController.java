package com.tiptracker.api;

import com.tiptracker.api.register.RegisterRequest;
import com.tiptracker.api.register.RegisterResponse;
import com.tiptracker.api.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @Autowired
    private RegisterService registerService;

    @PostMapping(path = "/register")
    public @ResponseBody RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        return registerService.getResponse(registerRequest);
    }

//    @Autowired
//    private LoginService loginService;

//    @PostMapping(path = "/login")
//    public @ResponseBody LoginResponse login(@RequestBody LoginRequest loginRequest){
//        return loginService.getResponse(loginRequest);
//    }

}