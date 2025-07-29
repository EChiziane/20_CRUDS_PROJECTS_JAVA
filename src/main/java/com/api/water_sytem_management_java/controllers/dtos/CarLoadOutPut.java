package com.api.water_sytem_management_java.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CarLoadOutPut(
        UUID id,
        String deliveryDestination,    // Final delivery location
        String customerName,           // Name of the customer receiving materials
        String logisticsManagerName,
        String logisticsManagerId,// Person managing the shipment
        String assignedDriverName,
        String assignedDriverId,// Driver responsible for the delivery
        String transportedMaterial,    // Name/type of material being delivered
        String carloadBatchName,
        String carloadBatchId,// Name of the delivery sprint or batch
        String customerPhoneNumber,    // Contact phone number of the customer
        BigDecimal totalSpent,         // Money spent on the delivery
        BigDecimal totalEarnings,
        LocalDateTime   deliveryScheduledDate,     // Revenue from the delivery
        CarLoadStatus deliveryStatus          // Current status: e.g., "pending", "completed"
) {


}
