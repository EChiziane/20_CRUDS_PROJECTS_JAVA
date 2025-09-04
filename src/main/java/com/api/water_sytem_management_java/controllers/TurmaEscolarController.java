package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.TurmaEscolarInput;
import com.api.water_sytem_management_java.controllers.dtos.TurmaEscolarOutput;
import com.api.water_sytem_management_java.services.TurmaEscolarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/turmas-escolares")
public class TurmaEscolarController {

    private final TurmaEscolarService turmaEscolarService;

    public TurmaEscolarController(TurmaEscolarService turmaEscolarService) {
        this.turmaEscolarService = turmaEscolarService;
    }

    @GetMapping
    public ResponseEntity<List<TurmaEscolarOutput>> getAllClasses() {
        List<TurmaEscolarOutput> classes = turmaEscolarService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @PostMapping
    public ResponseEntity<TurmaEscolar> createClass(@RequestBody TurmaEscolarInput input) {
        TurmaEscolar turmaEscolar = input.toTurmaEscolar();
        TurmaEscolar savedClass = turmaEscolarService.createClass(turmaEscolar);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaEscolarOutput> updateClass(@PathVariable UUID id, @RequestBody TurmaEscolarInput input) {
        Optional<TurmaEscolarOutput> updatedClass = turmaEscolarService.updateClass(id, input);
        return updatedClass.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable UUID id) {
        turmaEscolarService.deleteClass(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}