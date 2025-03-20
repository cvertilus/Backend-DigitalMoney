package com.example.login_service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String id;
    private String nyap;
    private String dni;
    private String email;
    private String telefono;
    private String cvu;
    private String alias;

    public UserDTO(String id, String nyap, String dni, String email, String telefono, String cvu, String alias) {
        this.id = id;
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.cvu = cvu;
        this.alias = alias;
    }

}