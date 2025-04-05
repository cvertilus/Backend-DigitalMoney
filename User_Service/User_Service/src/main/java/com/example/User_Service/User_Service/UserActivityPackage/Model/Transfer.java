package com.example.User_Service.User_Service.UserActivityPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    private  String origin;
    private String destino;
    private Long cantitdad;

}
