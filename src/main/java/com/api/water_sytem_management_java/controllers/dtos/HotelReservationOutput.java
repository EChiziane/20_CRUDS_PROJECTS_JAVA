package com.api.water_sytem_management_java.controllers.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record HotelReservationOutput(UUID id, String guestName, LocalDate checkInDate, LocalDate checkOutDate,
                                     Integer roomNumber, Double totalPrice, LocalDateTime createdAt) {
}