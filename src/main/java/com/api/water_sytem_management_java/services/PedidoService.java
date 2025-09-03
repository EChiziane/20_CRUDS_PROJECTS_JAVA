package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.PedidoInput;
import com.api.water_sytem_management_java.controllers.PedidoOutput;
import com.api.water_sytem_management_java.models.Pedido;
import com.api.water_sytem_management_java.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoOutput> getAllPedidos() {
        return pedidoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(Pedido::toPedidoOutput)
                .collect(Collectors.toList());
    }

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Optional<PedidoOutput> updatePedido(UUID id, PedidoInput pedidoInput) {
        return pedidoRepository.findById(id)
                .map(existingPedido -> {
                    existingPedido.setDataPedido(pedidoInput.dataPedido());
                    existingPedido.setValorTotal(pedidoInput.valorTotal());
                    existingPedido.setStatus(pedidoInput.status());
                    existingPedido.setClienteId(pedidoInput.clienteId());
                    Pedido updatedPedido = pedidoRepository.save(existingPedido);
                    return updatedPedido.toPedidoOutput();
                });
    }

    public void deletePedido(UUID id) {
        pedidoRepository.deleteById(id);
    }
}