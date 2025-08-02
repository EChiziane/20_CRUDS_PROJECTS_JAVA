package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.TeacherInput;
import com.api.water_sytem_management_java.controllers.dtos.TeacherOutput;
import com.api.water_sytem_management_java.models.Teacher;
import com.api.water_sytem_management_java.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public TeacherOutput createTeacher(TeacherInput input) {
        Teacher teacher = input.toTeacher();
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherOutput.fromEntity(savedTeacher);
    }

    public List<TeacherOutput> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherOutput::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<TeacherOutput> getTeacherById(UUID id) {
        return teacherRepository.findById(id).map(TeacherOutput::fromEntity);
    }

    @Transactional
    public Optional<TeacherOutput> updateTeacher(UUID id, TeacherInput input) {
        return teacherRepository.findById(id).map(existing -> {
            existing.setName(input.name());
            existing.setEmail(input.email());
            existing.setPhoneNumber(input.phoneNumber());
            existing.setSpecialization(input.specialization());
            existing.setType(input.type());
            Teacher updated = teacherRepository.save(existing);
            return TeacherOutput.fromEntity(updated);
        });
    }

    public void deleteTeacher(UUID id) {
        teacherRepository.deleteById(id);
    }
}
