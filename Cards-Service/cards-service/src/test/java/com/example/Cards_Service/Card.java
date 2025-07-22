package com.example.Cards_Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private String cardId;
    private String expiration;
    private String number;
    private String name;
    private String cvc;
    private String userId;

}
