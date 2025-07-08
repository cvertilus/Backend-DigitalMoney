package com.example.login.login_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Model representing the data required to create a new user.")
public class CreateUser {
    private String firstname;
    private String email;
    private String password;
    private String lastname;
    private String phone;
    private String dni;

}
