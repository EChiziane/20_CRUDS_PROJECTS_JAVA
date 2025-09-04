package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.HotelReservation;

import java.time.LocalDate;

public record HotelReservationInput(String guestName, LocalDate checkInDate, LocalDate checkOutDate, Integer roomNumber, Double totalPrice) {

    public HotelReservation toHotelReservation() {
        return new HotelReservation(guestName, checkInDate, checkOutDate, roomNumber, totalPrice);
    }
}