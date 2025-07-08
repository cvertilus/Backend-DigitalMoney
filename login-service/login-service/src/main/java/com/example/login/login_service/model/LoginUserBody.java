package com.example.login.login_service.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Model representing the data required for user login.")
public class LoginUserBody {
    private String username;
    private String password;

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
}
