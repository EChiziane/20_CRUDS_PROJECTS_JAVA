package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Employee;

import java.time.LocalDate;

public record EmployeeInput(
        String name,
        String position,
        Double salary,
        LocalDate hireDate,
        String department
) {
    public Employee toEmployee() {
        return new Employee(name, position, salary, hireDate, department);
    }
}