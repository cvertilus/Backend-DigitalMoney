package com.example.Cards_Service.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tarjetas")
@Builder(toBuilder = true)
public class Tarjeta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String expiration;

    @Column(nullable = false,unique = true)
    private String number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private String userId;

}

