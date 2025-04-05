package com.example.User_Service.User_Service.UserActivityPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Activity {
    private String id;
    private String name;
    private Long amount;
    private String date;
    private String userId;
    private  String origin;
    private String destination;
    private String type;
    private String description;

    public Activity(String name, Long amount, String userId, String origin, String destination, String type) {
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.type = type;
    }

    public Activity(String name, Long amount, String userId, String type, String description) {
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.type = type;
        this.description = description;
    }
}
