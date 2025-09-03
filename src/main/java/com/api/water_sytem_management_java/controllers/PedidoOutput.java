package com.api.water_sytem_management_java.controllers;


import com.api.water_sytem_management_java.controllers.dtos.PedidoStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoOutput(UUID id, LocalDateTime dataPedido, Double valorTotal, PedidoStatus status,
                           UUID clienteId, LocalDateTime createdAt) {
}