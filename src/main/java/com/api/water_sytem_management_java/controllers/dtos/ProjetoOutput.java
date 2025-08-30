package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.ProjetoStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjetoOutput(UUID id, String nome, String descricao, String dataInicio, String dataFim,
                            ProjetoStatus status, LocalDateTime createdAt) {
}
