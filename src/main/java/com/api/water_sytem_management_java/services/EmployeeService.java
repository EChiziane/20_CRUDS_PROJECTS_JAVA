package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.EmployeeInput;
import com.api.water_sytem_management_java.controllers.dtos.EmployeeOutput;
import com.api.water_sytem_management_java.models.Employee;
import com.api.water_sytem_management_java.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOutput> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(Employee::toEmployeeOutput)
                .collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Optional<EmployeeOutput> updateEmployee(UUID id, EmployeeInput input) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(input.name());
                    existingEmployee.setPosition(input.position());
                    existingEmployee.setSalary(input.salary());
                    existingEmployee.setHireDate(input.hireDate());
                    existingEmployee.setDepartment(input.department());
                    Employee updatedEmployee = employeeRepository.save(existingEmployee);
                    return mapToEmployeeOutput(updatedEmployee);
                });
    }

    private EmployeeOutput mapToEmployeeOutput(Employee employee) {
        return employee.toEmployeeOutput();
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}