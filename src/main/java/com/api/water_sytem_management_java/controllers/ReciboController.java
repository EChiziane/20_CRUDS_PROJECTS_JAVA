package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.ReciboInput;
import com.api.water_sytem_management_java.controllers.dtos.ReciboOutPut;
import com.api.water_sytem_management_java.models.Recibo;
import com.api.water_sytem_management_java.services.ReciboService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/recibos")
public class ReciboController {

    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @PostMapping
    public ResponseEntity<Recibo> createRecibo(@RequestBody ReciboInput input) throws IOException {
        Recibo saved = reciboService.createRecibo(input.paymentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ReciboOutPut>> getAllRecibos() {
        return ResponseEntity.ok(reciboService.getAllRecibos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReciboOutPut> updateRecibo(@PathVariable UUID id, @RequestBody ReciboInput input) {
        Optional<ReciboOutPut> updated = reciboService.updateRecibo(id, input);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecibo(@PathVariable UUID id) {
        reciboService.deleteRecibo(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadRecibo(@PathVariable UUID id) throws IOException {
        Recibo recibo = reciboService.getReciboEntity(id)
                .orElseThrow(() -> new IllegalArgumentException("Recibo não encontrado"));

        File file = new File(recibo.getFilePath());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileContent = java.nio.file.Files.readAllBytes(file.toPath());

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + recibo.getFileName() + "\"")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(fileContent);
    }


}
