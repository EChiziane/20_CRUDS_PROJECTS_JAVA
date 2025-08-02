package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.ClassroomInput;
import com.api.water_sytem_management_java.controllers.dtos.ClassroomOutput;
import com.api.water_sytem_management_java.services.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<ClassroomOutput> createClassroom(@RequestBody ClassroomInput input) {
        var classroom = classroomService.createClassroom(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                classroomService.getClassroomById(classroom.getId()).get()
        );
    }

    @GetMapping
    public ResponseEntity<List<ClassroomOutput>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomOutput> getClassroomById(@PathVariable UUID id) {
        return classroomService.getClassroomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomOutput> updateClassroom(@PathVariable UUID id, @RequestBody ClassroomInput input) {
        return classroomService.updateClassroom(id, input)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable UUID id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
