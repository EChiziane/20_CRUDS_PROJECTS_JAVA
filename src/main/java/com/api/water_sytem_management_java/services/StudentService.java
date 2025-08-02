package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.StudentInput;
import com.api.water_sytem_management_java.controllers.dtos.StudentOutput;
import com.api.water_sytem_management_java.models.Student;
import com.api.water_sytem_management_java.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<StudentOutput> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentOutput)
                .collect(Collectors.toList());
    }

    public Optional<StudentOutput> getStudentById(UUID id) {
        return studentRepository.findById(id).map(this::mapToStudentOutput);
    }

    @Transactional
    public Optional<StudentOutput> updateStudent(UUID id, StudentInput input) {
        return studentRepository.findById(id).map(student -> {
            student.setNome(input.nome());
            student.setNumeroEstudante(input.numeroEstudante());
            student.setBi(input.bi());
            student.setDataNascimento(input.dataNascimento());
            student.setEndereco(input.endereco());
            student.setNivelAcademico(input.nivelAcademico());
            student.setUltimoNivelIngles(input.ultimoNivelIngles());
            return mapToStudentOutput(studentRepository.save(student));
        });
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    private StudentOutput mapToStudentOutput(Student student) {
        return new StudentOutput(
                student.getId(),
                student.getNome(),
                student.getNumeroEstudante(),
                student.getBi(),
                student.getDataNascimento(),
                student.getEndereco(),
                student.getNivelAcademico(),
                student.getUltimoNivelIngles()
        );
    }
}
