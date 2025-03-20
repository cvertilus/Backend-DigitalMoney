package com.example.login_service.model;

import lombok.Getter;

import lombok.Lombok;
import lombok.Setter;

@lombok.Setter
@lombok.Getter
public class User {
    private String id;
    private String username;
    private String nyap;
    private String dni;
    private String email;
    private String telefono;
    private String password;
    private String cvu;
    private String alias;

    public User () {}

    public User(String id, String username,String nyap, String dni, String email, String telefono, String password, String cvu, String alias) {
        this.id = id;
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cvu = cvu;
        this.alias = alias;
    }

    public User(String username, String password) {
        this.username= username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNyap() {
        return nyap;
    }

    public void setNyap(String nyap) {
        this.nyap = nyap;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
