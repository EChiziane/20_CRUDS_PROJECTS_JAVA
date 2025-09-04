package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Turno;

import java.time.LocalDateTime;
import java.util.UUID;

public record TurmaEscolarOutput(
        UUID id,
        String nomeTurma,
        Integer capacidade,
        Turno turno,
        String curso,
        LocalDateTime createdAt
) {}