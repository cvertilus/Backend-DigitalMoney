package com.digitalMoney.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "accounts")
@Schema(
        description = "Represents a user's account in the system.",
        title = "Account",
        requiredProperties = {"userId", "balance", "cvu", "alias", "name"}
)

@NoArgsConstructor
@Getter

public class Account {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private int balance;

    @Column(nullable = false, unique = true)
    private String cvu;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false, unique = true)
    private String name;

    public Account(String userId, int balance, String cvu, String alias, String name) {
        this.userId = userId;
        this.balance = balance;
        this.cvu = cvu;
        this.alias = alias;
        this.name = name;
    }

    public Account(Long accountId, String userId, int balance, String cvu, String alias, String name) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.cvu = cvu;
        this.alias = alias;
        this.name = name;
    }
}
