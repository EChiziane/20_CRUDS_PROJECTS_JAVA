package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.VehicleInput;
import com.api.water_sytem_management_java.controllers.dtos.VehicleOutput;
import com.api.water_sytem_management_java.models.Vehicle;
import com.api.water_sytem_management_java.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleOutput>> getAllVehicles() {
        List<VehicleOutput> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleInput vehicleInput) {
        Vehicle vehicle = vehicleInput.toVehicle();
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleOutput> updateVehicle(@PathVariable UUID id, @RequestBody VehicleInput vehicleInput) {
        Optional<VehicleOutput> updatedVehicle = vehicleService.updateVehicle(id, vehicleInput);
        return updatedVehicle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
