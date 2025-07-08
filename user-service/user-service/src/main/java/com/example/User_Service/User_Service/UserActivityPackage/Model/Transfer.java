package com.example.User_Service.User_Service.UserActivityPackage.Model;

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
        description = "Transfer model representing a transfer activity in the system. " +
                "Contains details such as origin, destination, and quantity of the transfer.")
public class Transfer {
    private  String origin;
    private String destino;
    private Long cantitdad;

}
