package com.example.login.login_service.model;

public class UserP {

  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String telefono;
  private String username;
  private String cvu;
  private String alias;

  public UserP(String userId, String firstName, String lastName, String email, String username) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username=username;
  }

  public UserP(String userId, String username, String telefono, String email, String cvu, String alias) {
    this.userId = userId;
    this.username = username;
    this.telefono = telefono;
    this.email = email;
    this.cvu = cvu;
    this.alias = alias;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
