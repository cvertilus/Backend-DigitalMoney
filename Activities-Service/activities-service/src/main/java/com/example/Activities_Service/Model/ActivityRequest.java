package com.example.Activities_Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Activity Request Model",
        title = "Activity Request")
public class ActivityRequest {

    private Long amount;
    private String type;
    private String description;
    private String origin;
    private String destination;
    private String name;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
