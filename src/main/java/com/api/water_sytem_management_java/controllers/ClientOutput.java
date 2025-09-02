package com.api.water_sytem_management_java.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientOutput(
        UUID id,
        String name,
        String email,
        String phone,
        String address,
        LocalDateTime registrationDate
) {
}