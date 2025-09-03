package com.api.water_sytem_management_java.models;

import com.api.water_sytem_management_java.controllers.dtos.PedidoStatus;
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
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime dataPedido;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    private UUID clienteId;

    public Pedido() {
    }

    public Pedido(LocalDateTime dataPedido, Double valorTotal, PedidoStatus status, UUID clienteId) {
        if (valorTotal == null || valorTotal <= 0) {
            throw new IllegalArgumentException("O valor total do pedido deve ser maior que zero.");
        }
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.status = status;
        this.clienteId = clienteId;
    }

    public PedidoOutput toPedidoOutput() {
        return new PedidoOutput(id, dataPedido, valorTotal, status, clienteId, createdAt);
    }
}