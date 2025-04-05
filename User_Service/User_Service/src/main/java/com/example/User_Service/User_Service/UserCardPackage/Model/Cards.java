package com.example.User_Service.User_Service.UserCardPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Cards {
    private String id;
    private String expiration;
    private String number;
    private String name;
    private String userId;
    private String cvc;

    public Cards(String expiration, String number, String name, String userId, String cvc) {
        this.expiration = expiration;
        this.number = number;
        this.name = name;
        this.userId = userId;
        this.cvc = cvc;
    }

}
