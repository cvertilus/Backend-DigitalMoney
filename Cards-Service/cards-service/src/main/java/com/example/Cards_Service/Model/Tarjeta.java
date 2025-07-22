package com.example.Cards_Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Schema(
        description = "Tarjeta Model",
        title = "Tarjeta"
)
public class Tarjeta {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "card_id", nullable = false, updatable = false)
    private String cardId;

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

    public Tarjeta(String expiration, String number, String name, String cvc, String userId) {
        this.expiration = expiration;
        this.number = number;
        this.name = name;
        this.cvc = cvc;
        this.userId = userId;
    }
}

