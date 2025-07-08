package com.example.login.login_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String userId;
    private int balance;
    private String cvu;
    private String alias;
    private String name;

}
