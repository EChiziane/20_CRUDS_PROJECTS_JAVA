package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.VehicleInput;
import com.api.water_sytem_management_java.controllers.dtos.VehicleOutput;
import com.api.water_sytem_management_java.models.Vehicle;
import com.api.water_sytem_management_java.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleOutput> getAllVehicles() {
        return vehicleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(Vehicle::toVehicleOutput)
                .collect(Collectors.toList());
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Optional<VehicleOutput> updateVehicle(UUID id, VehicleInput input) {
        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    existingVehicle.setModel(input.model());
                    existingVehicle.setBrand(input.brand());
                    existingVehicle.setYear(input.year());
                    existingVehicle.setLicensePlate(input.licensePlate());
                    existingVehicle.setMileage(input.mileage());
                    Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
                    return mapToVehicleOutput(updatedVehicle);
                });
    }

    private VehicleOutput mapToVehicleOutput(Vehicle vehicle) {
        return vehicle.toVehicleOutput();
    }

    public void deleteVehicle(UUID id) {
        vehicleRepository.deleteById(id);
    }
}
