package com.digitalMoney.demo.model;

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
        description = "Represents a transfer request between accounts.",
        title = "Transfer Request",
        requiredProperties = {"origin", "destino", "cantitad"}
)
public class TransferRequest {
    @NotNull(message = "Origin account cannot be null")
    @NotBlank(message = "Origin account cannot be blank")
    @Schema(description = "The account from which the funds will be transferred.")
    private String origin;

    @NotNull(message = "Destination account cannot be null")
    @NotBlank(message = "Destination account cannot be blank")
    @Schema(description = "The account to which the funds will be transferred.")
    private String destino;

    @NotNull(message = "Amount cannot be null")
    @Schema(description = "The amount of money to be transferred.")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int cantidad;
}
