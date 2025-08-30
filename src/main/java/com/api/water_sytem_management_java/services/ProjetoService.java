package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.ProjetoInput;
import com.api.water_sytem_management_java.controllers.dtos.ProjetoOutput;
import com.api.water_sytem_management_java.models.Projeto;
import com.api.water_sytem_management_java.repositories.ProjetoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<ProjetoOutput> listarTodosProjetos() {
        return projetoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(Projeto::toProjetoOutput)
                .collect(Collectors.toList());
    }

    public Projeto criarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Transactional
    public Optional<ProjetoOutput> atualizarProjeto(UUID id, ProjetoInput input) {
        return projetoRepository.findById(id)
                .map(projetoExistente -> {
                    projetoExistente.setNome(input.nome());
                    projetoExistente.setDescricao(input.descricao());
                    projetoExistente.setDataInicio(input.dataInicio());
                    projetoExistente.setDataFim(input.dataFim());
                    projetoExistente.setStatus(input.status());

                    Projeto projetoAtualizado = projetoRepository.save(projetoExistente);
                    return projetoAtualizado.toProjetoOutput();
                });
    }

    public void excluirProjeto(UUID id) {
        projetoRepository.deleteById(id);
    }
}
