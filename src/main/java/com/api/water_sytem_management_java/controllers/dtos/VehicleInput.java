package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Vehicle;

public record VehicleInput(
        String model,
        String brand,
        Integer year,
        String licensePlate,
        Double mileage
) {
    public Vehicle toVehicle() {
        return new Vehicle(model, brand, year, licensePlate, mileage);
    }
}
