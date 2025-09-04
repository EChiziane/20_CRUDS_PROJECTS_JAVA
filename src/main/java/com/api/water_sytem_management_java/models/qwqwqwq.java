package com.api.water_sytem_management_java.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_hotel_reservations")
public class HotelReservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String guestName;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Integer roomNumber;

    private Double totalPrice;

    public HotelReservation() {}

    public HotelReservation(String guestName, LocalDate checkInDate, LocalDate checkOutDate, Integer roomNumber, Double totalPrice) {
        if (checkOutDate.isBefore(checkInDate)) {
            throw new IllegalArgumentException("Check-out date cannot be earlier than check-in date.");
        }
        if (totalPrice == null || totalPrice <= 0) {
            throw new IllegalArgumentException("Total price must be greater than zero.");
        }

        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
    }

    public HotelReservationOutput toHotelReservationOutput() {
        return new HotelReservationOutput(id, guestName, checkInDate, checkOutDate, roomNumber, totalPrice, createdAt);
    }
}