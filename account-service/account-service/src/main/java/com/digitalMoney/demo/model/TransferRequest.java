package com.digitalMoney.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String origin;
    private String destino;
    private int cantitad;
}
