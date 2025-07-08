package com.example.User_Service.User_Service.UserCardPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(
        description = "Card model representing a user's card information. " +
                "Contains details such as card number, expiration date, cardholder name, and CVC.")
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
