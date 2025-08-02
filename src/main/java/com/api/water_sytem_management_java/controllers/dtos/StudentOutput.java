package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.EnglishLevel;

import java.time.LocalDate;
import java.util.UUID;

public record StudentOutput(
        UUID id,
        String nome,
        String numeroEstudante,
        String bi,
        LocalDate dataNascimento,
        String endereco,
        String nivelAcademico,
        EnglishLevel ultimoNivelIngles
) {
}
