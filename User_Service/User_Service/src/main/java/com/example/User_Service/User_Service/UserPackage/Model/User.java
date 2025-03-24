package com.example.User_Service.User_Service.UserPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String telefono;
    private String username;
    private String dni;

}
