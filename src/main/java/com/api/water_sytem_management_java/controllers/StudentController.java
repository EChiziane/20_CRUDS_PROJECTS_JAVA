package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.StudentInput;
import com.api.water_sytem_management_java.controllers.dtos.StudentOutput;
import com.api.water_sytem_management_java.models.Student;
import com.api.water_sytem_management_java.services.ReciboService;
import com.api.water_sytem_management_java.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

    private final StudentService studentService;
    private final ReciboService reciboService;

    public StudentController(StudentService studentService, ReciboService reciboService) {
        this.studentService = studentService;
        this.reciboService = reciboService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentInput input) {
        Student student = input.toStudent();
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentOutput>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/recibo/{id}")
    ResponseEntity<StudentOutput> generateRecipt(@PathVariable UUID id) throws IOException {
        reciboService.atualizarStudentRecipt(id);
      //  reciboService.imprimir(recibo);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutput> getStudentById(@PathVariable UUID id) {
        Optional<StudentOutput> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutput> updateStudent(@PathVariable UUID id, @RequestBody StudentInput input) {
        Optional<StudentOutput> updated = studentService.updateStudent(id, input);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
