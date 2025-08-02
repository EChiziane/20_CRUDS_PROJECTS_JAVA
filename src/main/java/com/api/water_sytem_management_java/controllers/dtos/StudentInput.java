

package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.EnglishLevel;
import com.api.water_sytem_management_java.models.Student;

import java.time.LocalDate;

public record StudentInput(
        String nome,
        String numeroEstudante,
        String bi,
        LocalDate dataNascimento,
        String endereco,
        String nivelAcademico,
        EnglishLevel ultimoNivelIngles
) {
    public Student toStudent() {
        return new Student(nome, numeroEstudante, bi, dataNascimento, endereco, nivelAcademico, ultimoNivelIngles);
    }
}
