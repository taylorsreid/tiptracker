package com.tiptracker.api.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@Setter
@ResponseBody
public class RegisterResponse {

    private boolean availableEmail;
    private boolean validEmail;
    private boolean validPassword;
    private String message;

}