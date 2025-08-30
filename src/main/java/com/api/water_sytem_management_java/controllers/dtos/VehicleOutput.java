package com.api.water_sytem_management_java.controllers.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleOutput(
        UUID id,
        String model,
        String brand,
        Integer year,
        String licensePlate,
        Double mileage,
        LocalDateTime createdAt
) {
}
