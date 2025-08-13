package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.services.ReciboService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/recibos")
public class ReciboController {

    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarRecibo(@RequestParam String nome,
                                              @RequestParam String endereco,
                                              @RequestParam String data) throws IOException {
        File recibo = reciboService.atualizarRecibo(nome, endereco, data);
        reciboService.imprimir(recibo);
        return ResponseEntity.ok("✅ Recibo gerado e enviado para impressão!");
    }
}
