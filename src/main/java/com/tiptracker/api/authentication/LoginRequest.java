package com.tiptracker.api.authentication;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;

}
