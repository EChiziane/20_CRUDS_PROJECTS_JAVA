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
@Table(name = "tb_receipts1")
public class Recibo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String numeroRecibo;
    private LocalDate dataRecibo;
    private UUID idPayment;
    private String fileName;
    private String filePath;

    public Recibo() {
    }

    public Recibo(String numeroRecibo, UUID idPayment, String fileName, String filePath) {
        this.numeroRecibo = numeroRecibo;
        this.dataRecibo = LocalDateTime.now().toLocalDate();
        this.idPayment = idPayment;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public ReciboOutPut toReciboOutPut() {
        return new ReciboOutPut(
                id,
                idPayment,
fileName,
                filePath,
                createdAt
        );
    }
}
