package com.api.water_sytem_management_java.controllers.dtos;


import com.api.water_sytem_management_java.models.Projeto;
import com.api.water_sytem_management_java.models.ProjetoStatus;

public record ProjetoInput(String nome, String descricao, String dataInicio, String dataFim, ProjetoStatus status) {
    public Projeto toProjeto() {
        return new Projeto(nome, descricao, dataInicio, dataFim, status);
    }
}
