package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Teacher;
import com.api.water_sytem_management_java.models.TeacherType;

import java.util.UUID;

public record TeacherInput(
        String name,
        String email,
        String phoneNumber,
        String specialization,
        TeacherType type
) {
    public Teacher toTeacher() {
        return new Teacher(name, email, phoneNumber, specialization, type);
    }
}
