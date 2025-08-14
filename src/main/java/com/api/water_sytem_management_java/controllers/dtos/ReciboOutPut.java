package com.api.water_sytem_management_java.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReciboOutPut(
        UUID id,
        UUID paymentId,
        String fileName,
        String filePath,
        LocalDateTime createdAt
) {}
