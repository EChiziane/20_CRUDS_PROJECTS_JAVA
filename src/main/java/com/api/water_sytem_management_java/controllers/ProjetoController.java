package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.ProjetoInput;
import com.api.water_sytem_management_java.controllers.dtos.ProjetoOutput;
import com.api.water_sytem_management_java.models.Projeto;
import com.api.water_sytem_management_java.services.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<List<ProjetoOutput>> listarTodosProjetos() {
        List<ProjetoOutput> projetos = projetoService.listarTodosProjetos();
        return ResponseEntity.ok(projetos);
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody ProjetoInput projetoInput) {
        Projeto projeto = projetoInput.toProjeto();
        Projeto projetoSalvo = projetoService.criarProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoOutput> atualizarProjeto(@PathVariable UUID id, @RequestBody ProjetoInput projetoInput) {
        Optional<ProjetoOutput> projetoAtualizado = projetoService.atualizarProjeto(id, projetoInput);
        return projetoAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable UUID id) {
        projetoService.excluirProjeto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
