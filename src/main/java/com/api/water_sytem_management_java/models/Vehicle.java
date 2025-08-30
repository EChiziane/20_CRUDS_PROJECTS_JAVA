package com.api.water_sytem_management_java.models;


import com.api.water_sytem_management_java.controllers.dtos.VehicleOutput;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_vehicles1")
public class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String model;
    private String brand;
    private Integer year;
    private String licensePlate;
    private Double mileage;

    public Vehicle() {
    }

    public Vehicle(String model, String brand, Integer year, String licensePlate, Double mileage) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
    }

    public VehicleOutput toVehicleOutput() {
        return new VehicleOutput(id, model, brand, year, licensePlate, mileage, createdAt);
    }

    // Getters e setters
    public UUID getId() { return id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public Double getMileage() { return mileage; }
    public void setMileage(Double mileage) { this.mileage = mileage; }
}
