package com.api.water_sytem_management_java.models;



import com.api.water_sytem_management_java.controllers.dtos.ProjetoOutput;
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
@Table(name = "tb_projetos")
public class Projeto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    @Enumerated(EnumType.STRING)
    private ProjetoStatus status; // EM_ANDAMENTO, CONCLUIDO

    public Projeto() {}

    public Projeto(String nome, String descricao, String dataInicio, String dataFim, ProjetoStatus status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public ProjetoOutput toProjetoOutput() {
        return new ProjetoOutput(id, nome, descricao, dataInicio, dataFim, status, createdAt);
    }
}
