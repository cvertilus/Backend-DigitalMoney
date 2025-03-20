package com.example.login.login_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    private String firstname;
    private String email;
    private String password;
    private String lastname;
    private String phone;
    private String dni;

}
