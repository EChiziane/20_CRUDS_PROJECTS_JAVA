package com.api.water_sytem_management_java.controllers.dtos;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record BankAccountOutput(
        UUID id,
        String accountNumber,
        String accountHolder,
        Double balance,
        Double creditLimit,
        LocalDateTime openingDate,
        LocalDateTime createdAt
) {}