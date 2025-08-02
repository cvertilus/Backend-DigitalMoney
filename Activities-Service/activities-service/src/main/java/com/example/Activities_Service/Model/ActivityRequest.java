package com.example.Activities_Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Activity Request Model",
        title = "Activity Request")
public class ActivityRequest {

    @NotNull(message = "Amount cannot be null")
    @Schema(description = "Amount of the activity", example = "1000")
    @Min(value = 1, message = "Amount must be greater than  0")
    private Long amount;

    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Origin cannot be null")
    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotNull(message = "Destination cannot be null")
    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
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
