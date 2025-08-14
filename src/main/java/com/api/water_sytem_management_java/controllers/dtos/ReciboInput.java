package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Recibo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReciboInput(
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
        BigDecimal totalPagar,
        String fileName,
        String filePath
) {
    public Recibo toRecibo() {
        Recibo r = new Recibo();
        r.setNomeCliente(nomeCliente);
        r.setNumeroCliente(numeroCliente);
        r.setEnderecoCliente(enderecoCliente);
        r.setNumeroRecibo(numeroRecibo);
        r.setDataPagamento(dataPagamento);
        r.setDataRecibo(dataRecibo);
        r.setIdCliente(idCliente);
        r.setDescricaoProduto(descricaoProduto);
        r.setQuantidade(quantidade);
        r.setPrecoUnitario(precoUnitario);
        r.setTotalPagar(totalPagar);
        r.setFileName(fileName);
        r.setFilePath(filePath);
        return r;
    }
}
