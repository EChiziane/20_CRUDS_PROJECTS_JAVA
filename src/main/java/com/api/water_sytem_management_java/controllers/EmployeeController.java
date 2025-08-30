package com.api.water_sytem_management_java.controllers;


import com.api.water_sytem_management_java.controllers.dtos.EmployeeInput;
import com.api.water_sytem_management_java.controllers.dtos.EmployeeOutput;
import com.api.water_sytem_management_java.models.Employee;
import com.api.water_sytem_management_java.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeOutput>> getAllEmployees() {
        List<EmployeeOutput> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeInput employeeInput) {
        Employee employee = employeeInput.toEmployee();
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeOutput> updateEmployee(@PathVariable UUID id, @RequestBody EmployeeInput employeeInput) {
        Optional<EmployeeOutput> updatedEmployee = employeeService.updateEmployee(id, employeeInput);
        return updatedEmployee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
