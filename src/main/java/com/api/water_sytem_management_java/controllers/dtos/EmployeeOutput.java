package com.api.water_sytem_management_java.controllers.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeOutput(
        UUID id,
        String name,
        String position,
        Double salary,
        LocalDate hireDate,
        String department,
        LocalDateTime createdAt
) {
}