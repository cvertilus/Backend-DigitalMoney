package com.example.User_Service.User_Service.UserActivityPackage.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(
        description = "Activity model representing a user's activity in the system. " +
                "Contains details such as activity name, amount, date, user ID, origin, destination, type, and description.")

public class Activity {
    private String activityId;
    private String name;
    private double amount;
    private String date;
    private String userId;
    private  String origin;
    private String destination;
    private String type;
    private String description;

    public Activity(String name, double amount, String userId, String origin, String destination, String type) {
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
