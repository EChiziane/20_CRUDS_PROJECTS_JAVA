package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.TurmaEscolar;
import com.api.water_sytem_management_java.controllers.dtos.TurmaEscolarInput;
import com.api.water_sytem_management_java.controllers.dtos.TurmaEscolarOutput;
import com.api.water_sytem_management_java.repositories.TurmaEscolarRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TurmaEscolarService {

    private final TurmaEscolarRepository turmaEscolarRepository;

    public TurmaEscolarService(TurmaEscolarRepository turmaEscolarRepository) {
        this.turmaEscolarRepository = turmaEscolarRepository;
    }

    public List<TurmaEscolarOutput> getAllClasses() {
        return turmaEscolarRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(TurmaEscolar::toTurmaEscolarOutput)
                .collect(Collectors.toList());
    }

    public TurmaEscolar createClass(TurmaEscolar turmaEscolar) {
        if (turmaEscolar.getCapacidade() < 10 || turmaEscolar.getCapacidade() > 50) {
            throw new IllegalArgumentException("Class capacity must be between 10 and 50.");
        }
        return turmaEscolarRepository.save(turmaEscolar);
    }

    @Transactional
    public Optional<TurmaEscolarOutput> updateClass(UUID id, TurmaEscolarInput input) {
        return turmaEscolarRepository.findById(id).map(existingClass -> {
            existingClass.setNomeTurma(input.nomeTurma());
            if (input.capacidade() < 10 || input.capacidade() > 50) {
                throw new IllegalArgumentException("Class capacity must be between 10 and 50.");
            }
            existingClass.setCapacidade(input.capacidade());
            existingClass.setTurno(input.turno());
            existingClass.setCurso(input.curso());

            TurmaEscolar updatedClass = turmaEscolarRepository.save(existingClass);
            return updatedClass.toTurmaEscolarOutput();
        });
    }

    public void deleteClass(UUID id) {
        turmaEscolarRepository.deleteById(id);
    }
}