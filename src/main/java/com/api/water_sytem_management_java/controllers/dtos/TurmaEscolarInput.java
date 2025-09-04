package com.api.water_sytem_management_java.controllers.dtos;


import com.api.water_sytem_management_java.controllers.TurmaEscolar;
import com.api.water_sytem_management_java.models.Turno;

public record TurmaEscolarInput(
        String nomeTurma,
        Integer capacidade,
        Turno turno,
        String curso
) {
    public TurmaEscolar toTurmaEscolar() {
        return new TurmaEscolar(nomeTurma, capacidade, turno, curso);
    }
}