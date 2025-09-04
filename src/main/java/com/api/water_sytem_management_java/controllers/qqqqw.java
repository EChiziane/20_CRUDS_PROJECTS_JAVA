package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.models.Turno;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_turmas_escolares")
public class TurmaEscolar implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeTurma;

    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    private String curso;

    public TurmaEscolar() {}

    public TurmaEscolar(String nomeTurma, Integer capacidade, Turno turno, String curso) {
        if (capacidade < 10 || capacidade > 50) {
            throw new IllegalArgumentException("A capacidade deve estar entre 10 e 50.");
        }
        this.nomeTurma = nomeTurma;
        this.capacidade = capacidade;
        this.turno = turno;
        this.curso = curso;
    }

    public TurmaEscolarOutput toTurmaEscolarOutput() {
        return new TurmaEscolarOutput(id, nomeTurma, capacidade, turno, curso, createdAt);
    }
}