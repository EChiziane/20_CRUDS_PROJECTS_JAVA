package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.TeacherInput;
import com.api.water_sytem_management_java.controllers.dtos.TeacherOutput;
import com.api.water_sytem_management_java.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherOutput> createTeacher(@RequestBody TeacherInput input) {
        TeacherOutput created = teacherService.createTeacher(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TeacherOutput>> getAllTeachers() {
        List<TeacherOutput> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutput> getTeacherById(@PathVariable UUID id) {
        Optional<TeacherOutput> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherOutput> updateTeacher(@PathVariable UUID id, @RequestBody TeacherInput input) {
        Optional<TeacherOutput> updated = teacherService.updateTeacher(id, input);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
