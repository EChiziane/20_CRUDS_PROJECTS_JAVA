package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.PedidoInput;
import com.api.water_sytem_management_java.models.Pedido;
import com.api.water_sytem_management_java.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoOutput>> getAllPedidos() {
        List<PedidoOutput> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoInput pedidoInput) {
        Pedido pedido = pedidoInput.toPedido();
        Pedido savedPedido = pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoOutput> updatePedido(@PathVariable UUID id, @RequestBody PedidoInput pedidoInput) {
        Optional<PedidoOutput> updatedPedido = pedidoService.updatePedido(id, pedidoInput);
        return updatedPedido.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable UUID id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
