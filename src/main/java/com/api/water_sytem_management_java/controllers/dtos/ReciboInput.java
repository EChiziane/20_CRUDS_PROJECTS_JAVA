package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Payment;
import com.api.water_sytem_management_java.models.Recibo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReciboInput(

        UUID paymentId

) {
    public Recibo toRecibo() {
        Recibo r = new Recibo();
        r.setNumeroRecibo("numeroRecibo");
        r.setDataRecibo(LocalDateTime.now().toLocalDate());
        r.setIdPayment(paymentId);
        return r;
    }
}
