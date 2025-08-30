package com.api.water_sytem_management_java.repositories;

import com.api.water_sytem_management_java.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}