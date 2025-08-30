package com.api.water_sytem_management_java.controllers.dtos;


import java.time.LocalDateTime;
import java.util.UUID;

public record ProductOutput(
        UUID id,
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        String category,
        LocalDateTime createdAt
) {
}
