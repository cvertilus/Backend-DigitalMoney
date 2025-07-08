package com.example.Cards_Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(
        description = "Card Request Model",
        title = "Card Request"
)
public class CardRequest {
    private String name;
    private String cvc;
    private String number;
    private String expiration;

    public CardRequest(String name, String cvc, String number, String expiration) {
        this.name = name;
        this.cvc = cvc;
        this.number = number;
        this.expiration = expiration;
    }
}
