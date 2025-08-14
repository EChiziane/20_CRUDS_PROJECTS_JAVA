package com.api.water_sytem_management_java.models;

import com.api.water_sytem_management_java.controllers.dtos.ReciboOutPut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_receipts")
public class Recibo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeCliente;
    private String numeroCliente;
    private String enderecoCliente;
    private String numeroRecibo;
    private LocalDate dataPagamento;
    private LocalDate dataRecibo;
    private UUID idCliente;
    private String descricaoProduto;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal totalPagar;

    private String fileName;
    private String filePath;

    public Recibo() {
    }

    public Recibo(String nomeCliente,
                  String numeroCliente,
                  String enderecoCliente,
                  String numeroRecibo,
                  LocalDate dataPagamento,
                  LocalDate dataRecibo,
                  UUID idCliente,
                  String descricaoProduto,
                  int quantidade,
                  BigDecimal precoUnitario,
                  BigDecimal totalPagar) {
    }

    public ReciboOutPut toReciboOutPut() {
        return new ReciboOutPut(
                id,
                nomeCliente,
                numeroCliente,
                enderecoCliente,

                createdAt
        );
    }
}
