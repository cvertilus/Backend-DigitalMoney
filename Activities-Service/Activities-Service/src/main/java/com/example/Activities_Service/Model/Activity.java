package com.example.Activities_Service.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, updatable = false,insertable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String type;

    private String origin;
    private String destination;
    private String description;


}
