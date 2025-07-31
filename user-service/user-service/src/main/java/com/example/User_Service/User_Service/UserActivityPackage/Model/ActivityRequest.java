package com.example.User_Service.User_Service.UserActivityPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    private Long amount;
    private String type;
    private String description;
    private String origin;
    private String destination;
    private String name;
}
