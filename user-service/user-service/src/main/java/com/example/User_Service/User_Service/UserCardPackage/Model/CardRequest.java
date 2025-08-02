package com.example.User_Service.User_Service.UserCardPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "Name on the card", example = "John Doe")
    private String name;

    @NotNull(message = "CVC cannot be null")
    @NotBlank(message = "CVC cannot be blank")
    private String cvc;

    @NotNull(message = "Card number cannot be null")
    @NotBlank(message = "Card number cannot be blank")
    private String number;

    @NotNull(message = "Expiration date cannot be null")
    @NotBlank(message = "Expiration date cannot be blank")
    private String expiration;

    public CardRequest(String name, String cvc, String number, String expiration) {
        this.name = name;
        this.cvc = cvc;
        this.number = number;
        this.expiration = expiration;
    }
}
