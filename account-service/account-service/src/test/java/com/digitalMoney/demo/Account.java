package com.digitalMoney.demo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long accountId;
    private String userId;
    private int balance;
    private String cvu;
    private String alias;
    private String name;
}
