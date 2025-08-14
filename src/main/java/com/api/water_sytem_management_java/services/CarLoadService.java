package com.api.water_sytem_management_java.services;


import com.api.water_sytem_management_java.controllers.dtos.CarLoadInput;
import com.api.water_sytem_management_java.controllers.dtos.CarLoadOutPut;
import com.api.water_sytem_management_java.controllers.dtos.CarLoadStatus;
import com.api.water_sytem_management_java.models.CarLoad;
import com.api.water_sytem_management_java.models.Driver;
import com.api.water_sytem_management_java.models.Manager;
import com.api.water_sytem_management_java.models.Sprint;
import com.api.water_sytem_management_java.repositories.CarLoadRepository;
import com.api.water_sytem_management_java.repositories.DriverRepository;
import com.api.water_sytem_management_java.repositories.ManagerRepository;
import com.api.water_sytem_management_java.repositories.SprintRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarLoadService {

    private final CarLoadRepository carLoadRepository;
    private final ManagerRepository managerRepository;
    private final SprintRepository sprintRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public CarLoadService(CarLoadRepository carLoadRepository,
                          ManagerRepository managerRepository,
                          SprintRepository sprintRepository,
                          DriverRepository driverRepository) {
        this.carLoadRepository = carLoadRepository;
        this.sprintRepository = sprintRepository;
        this.driverRepository = driverRepository;
        this.managerRepository = managerRepository;
    }

    @Transactional
    public CarLoad createCarLoad(CarLoad carLoad) {
        return carLoadRepository.save(carLoad);
    }

    public List<CarLoadOutPut> getAllCarLoads() {
        return carLoadRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::mapToCarLoadOutput)
                .collect(Collectors.toList());
    }


    public List<CarLoadOutPut> getCarloadbySprint(UUID id) {
        return carLoadRepository.findByCarloadBatchSprintId(id)
                .stream()
                .map(this::mapToCarLoadOutput)
                .collect(Collectors.toList());
    }

    public CarLoadOutPut getCarLoadById(UUID id) {
        return carLoadRepository.findById(id)
                .map(this::mapToCarLoadOutput)
                .orElseThrow(() -> new CarLoadNotFoundException("No car load found with ID: " + id));
    }

    public void deleteCarLoad(UUID id) {
        carLoadRepository.deleteById(id);
    }


    private CarLoadOutPut mapToCarLoadOutput(CarLoad carLoad) {

        return carLoad.toCarLoadOutPut();
    }

    @Transactional
    public Optional<CarLoadOutPut> carloadUpdate(UUID id, CarLoadInput input) {
        return carLoadRepository.findById(id).map(existingCarload -> {

            // Atribuição de campos simples
            existingCarload.setDeliveryDestination(input.deliveryDestination());
            existingCarload.setCustomerName(input.customerName());
            existingCarload.setTransportedMaterial(input.transportedMaterial());
            existingCarload.setCustomerPhoneNumber(input.customerPhoneNumber());
            existingCarload.setTotalSpent(input.totalSpent());
            existingCarload.setTotalEarnings(input.totalEarnings());
            existingCarload.setDeliveryStatus(input.deliveryStatus());
            existingCarload.setDeliveryScheduledDate(input.deliveryScheduledDate());

            // Carregar entidades relacionadas (manager, driver, sprint)
            Manager manager = managerRepository.findById(input.logisticsManagerId())
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            Driver driver = driverRepository.findById(input.assignedDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
            Sprint sprint = sprintRepository.findById(input.carloadBatchId())
                    .orElseThrow(() -> new IllegalArgumentException("Sprint not found"));

            // Setar as associações no CarLoad
            existingCarload.setLogisticsManagerName(manager);
            existingCarload.setAssignedDriver(driver);
            existingCarload.setCarloadBatchSprint(sprint);

            // Persistir e retornar DTO
            CarLoad updatedCarLoad = carLoadRepository.save(existingCarload);
            return mapToCarLoadOutput(updatedCarLoad);
        });
    }

    @Transactional
    public Optional<CarLoadOutPut> encerarCarload(UUID id) {
        return carLoadRepository.findById(id).map(existingCarload -> {

            existingCarload.setDeliveryStatus(CarLoadStatus.DELIVERED);


            // Persistir e retornar DTO
            CarLoad updatedCarLoad = carLoadRepository.save(existingCarload);
            return mapToCarLoadOutput(updatedCarLoad);
        });
    }


}
