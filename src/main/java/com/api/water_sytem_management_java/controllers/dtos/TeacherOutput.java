package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Teacher;
import com.api.water_sytem_management_java.models.TeacherType;

import java.util.UUID;

public record TeacherOutput(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        String specialization,
        TeacherType type
) {
    public static TeacherOutput fromEntity(Teacher teacher) {
        return new TeacherOutput(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getPhoneNumber(),
                teacher.getSpecialization(),
                teacher.getType()
        );
    }
}
