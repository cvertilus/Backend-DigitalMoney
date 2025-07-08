package com.example.User_Service.User_Service.UserPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "User model representing a user in the system. " +
                "Contains personal information such as name, email, and contact details.")
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String telefono;
    private String username;
    private String dni;

}
