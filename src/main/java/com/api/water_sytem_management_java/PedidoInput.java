package com.api.water_sytem_management_java;




import com.api.water_sytem_management_java.controllers.dtos.PedidoStatus;
import com.api.water_sytem_management_java.models.Pedido;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoInput(LocalDateTime dataPedido, Double valorTotal, PedidoStatus status, UUID clienteId) {

    public Pedido toPedido() {
        return new Pedido(dataPedido, valorTotal, status, clienteId);
    }
}
