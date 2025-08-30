package com.api.water_sytem_management_java.models;

import com.api.water_sytem_management_java.controllers.dtos.EmployeeOutput;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_employees")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String position;
    private Double salary;
    private LocalDate hireDate;
    private String department;

    public Employee() {
    }

    public Employee(String name, String position, Double salary, LocalDate hireDate, String department) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
    }

    public EmployeeOutput toEmployeeOutput() {
        return new EmployeeOutput(id, name, position, salary, hireDate, department, createdAt);
    }

    // Getters e setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}