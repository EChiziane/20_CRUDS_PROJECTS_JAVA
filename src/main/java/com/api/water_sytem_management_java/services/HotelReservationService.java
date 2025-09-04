package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.HotelReservationInput;
import com.api.water_sytem_management_java.controllers.dtos.HotelReservationOutput;
import com.api.water_sytem_management_java.models.HotelReservation;
import com.api.water_sytem_management_java.repositories.HotelReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelReservationService {

    private final HotelReservationRepository hotelReservationRepository;

    public HotelReservationService(HotelReservationRepository hotelReservationRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
    }

    public List<HotelReservationOutput> getAllReservations() {
        return hotelReservationRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(HotelReservation::toHotelReservationOutput)
                .collect(Collectors.toList());
    }

    public HotelReservation createReservation(HotelReservation reservation) {
        return hotelReservationRepository.save(reservation);
    }

    @Transactional
    public Optional<HotelReservationOutput> updateReservation(UUID id, HotelReservationInput reservationInput) {
        return hotelReservationRepository.findById(id)
                .map(existingReservation -> {
                    existingReservation.setGuestName(reservationInput.guestName());
                    existingReservation.setCheckInDate(reservationInput.checkInDate());
                    existingReservation.setCheckOutDate(reservationInput.checkOutDate());
                    existingReservation.setRoomNumber(reservationInput.roomNumber());
                    existingReservation.setTotalPrice(reservationInput.totalPrice());
                    HotelReservation updatedReservation = hotelReservationRepository.save(existingReservation);
                    return updatedReservation.toHotelReservationOutput();
                });
    }

    public void deleteReservation(UUID id) {
        hotelReservationRepository.deleteById(id);
    }
}