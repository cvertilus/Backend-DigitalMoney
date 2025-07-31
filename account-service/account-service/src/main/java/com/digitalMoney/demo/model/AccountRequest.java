package com.digitalMoney.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountRequest {
    private String name;
    private String userId;
}
