package com.api.water_sytem_management_java.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_students")
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String numeroEstudante;
    private String bi;
    private LocalDate dataNascimento;
    private String endereco;
    private String nivelAcademico;

    @Enumerated(EnumType.STRING)
    private EnglishLevel ultimoNivelIngles;

    public Student() {}

    public Student(String nome, String numeroEstudante, String bi, LocalDate dataNascimento,
                   String endereco, String nivelAcademico, EnglishLevel ultimoNivelIngles) {
        this.nome = nome;
        this.numeroEstudante = numeroEstudante;
        this.bi = bi;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.nivelAcademico = nivelAcademico;
        this.ultimoNivelIngles = ultimoNivelIngles;
    }
}
