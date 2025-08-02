package com.example.Activities_Service.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Schema(
        description = "Represents an activity in the system, such as a transaction or event.",
        title = "Activity",
        type = "object")
public class Activity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "activity_id", nullable = false, updatable = false)
    private String activityId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    @CreationTimestamp
    private LocalDateTime date;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String type;

    private String origin;
    private String destination;
    private String description;


}
