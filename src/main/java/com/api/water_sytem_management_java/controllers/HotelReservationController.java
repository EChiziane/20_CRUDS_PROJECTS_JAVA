package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.HotelReservationInput;
import com.api.water_sytem_management_java.controllers.dtos.HotelReservationOutput;
import com.api.water_sytem_management_java.models.HotelReservation;
import com.api.water_sytem_management_java.services.HotelReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hotel-reservations")
public class HotelReservationController {

    private final HotelReservationService hotelReservationService;

    public HotelReservationController(HotelReservationService hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

    @GetMapping
    public ResponseEntity<List<HotelReservationOutput>> getAllReservations() {
        List<HotelReservationOutput> reservations = hotelReservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<HotelReservation> createReservation(@RequestBody HotelReservationInput reservationInput) {
        HotelReservation reservation = reservationInput.toHotelReservation();
        HotelReservation savedReservation = hotelReservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelReservationOutput> updateReservation(@PathVariable UUID id, @RequestBody HotelReservationInput reservationInput) {
        Optional<HotelReservationOutput> updatedReservation = hotelReservationService.updateReservation(id, reservationInput);
        return updatedReservation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        hotelReservationService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}