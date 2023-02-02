package com.tiptracker.api.authentication;

import lombok.Getter;

@Getter
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
