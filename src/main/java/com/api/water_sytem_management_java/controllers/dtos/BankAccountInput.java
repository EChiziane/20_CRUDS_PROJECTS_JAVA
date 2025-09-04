package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public record BankAccountInput(
        String accountNumber,
        String accountHolder,
        Double balance,
        Double creditLimit,
        LocalDateTime openingDate
) {
    public BankAccount toBankAccount() {
        return new BankAccount(accountNumber, accountHolder, balance, creditLimit, openingDate);
    }
}