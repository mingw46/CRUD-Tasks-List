package com.example.demo.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty(message = "Provide valid username")
    private String username;


    @NotNull
    @NotEmpty(message = "Provide valid password")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty(message = "Provide valid email")
    @Email(message = "Provide valid email")
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
