package com.api.water_sytem_management_java.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReceiptInput(
        String nomeCliente,
        String numeroCliente,
        String enderecoCliente,
        String numeroRecibo,
        LocalDate dataPagamento,
        LocalDate dataRecibo,
        UUID idCliente,
        String descricaoProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal totalPagar
) {

}
