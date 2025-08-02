package com.example.User_Service.User_Service.UserActivityPackage.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {

    @NotNull(message = "Amount cannot be null")
    @Min(message = "Amount must be greater than 0", value = 1)
    private int amount;

    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type cannot be blank")
    private String type;

    private String description;

    @NotNull(message = "Origin cannot be null")
    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotNull(message = "Destination cannot be null")
    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
