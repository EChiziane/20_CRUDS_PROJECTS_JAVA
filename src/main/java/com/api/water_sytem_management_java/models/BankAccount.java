package com.api.water_sytem_management_java.models;

import com.api.water_sytem_management_java.controllers.dtos.BankAccountOutput;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_bank_accounts")
public class BankAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String accountNumber;
    private String accountHolder;

    private Double balance;
    private Double creditLimit;

    private LocalDateTime openingDate;

    public BankAccount() {}

    public BankAccount(String accountNumber, String accountHolder, Double balance, Double creditLimit, LocalDateTime openingDate) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.openingDate = openingDate;
    }

    public BankAccountOutput toBankAccountOutput() {
        return new BankAccountOutput(id, accountNumber, accountHolder, balance, creditLimit, openingDate, createdAt);
    }
}