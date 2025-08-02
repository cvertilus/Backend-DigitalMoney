package com.example.User_Service.User_Service.UserAccountPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        description = "Transfer model representing a transfer activity in the system. " +
                "Contains details such as origin, destination, and quantity of the transfer.")
public class TransferenciaRequest {
    @NotNull(message = "Origin cannot be null")
    @NotBlank(message = "Origin cannot be blank")
    private  String origin;

    @NotNull(message = "Destination cannot be null")
    @NotBlank(message = "Destination cannot be blank")
    private String destino;

    @NotNull(message = "Quantity cannot be null")
    @NotBlank(message = "Quantity cannot be blank")
    @Min(message = "Quantity must be greater than 0", value = 1)
    private int cantidad;

}
