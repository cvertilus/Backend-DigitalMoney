package com.example.login.login_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(
        description = "Model representing the data required to create a new user.")
public class CreateUserDto {

    private String firstname;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private String cvu;
    private String alias;
    private String userId;

}
