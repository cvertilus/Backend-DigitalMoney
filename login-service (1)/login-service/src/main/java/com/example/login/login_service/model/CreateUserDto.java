package com.example.login.login_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
