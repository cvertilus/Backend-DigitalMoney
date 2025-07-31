package com.example.Activities_Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String activityId;
    private Long amount;
    private String type;
    private String description;
    private String origin;
    private String destination;
    private String name;
    private String date;
    private String userId;

}
