package com.api.water_sytem_management_java.services;


import com.api.water_sytem_management_java.controllers.ClientOutput;
import com.api.water_sytem_management_java.controllers.dtos.ClientInput;
import com.api.water_sytem_management_java.models.Client;
import com.api.water_sytem_management_java.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientOutput> getAllClients() {
        return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "registrationDate")).stream()
                .map(Client::toClientOutput)
                .collect(Collectors.toList());
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Optional<ClientOutput> updateClient(UUID id, ClientInput input) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setName(input.name());
                    existingClient.setEmail(input.email());
                    existingClient.setPhone(input.phone());
                    existingClient.setAddress(input.address());
                    Client updatedClient = clientRepository.save(existingClient);
                    return mapToClientOutput(updatedClient);
                });
    }

    private ClientOutput mapToClientOutput(Client client) {
        return client.toClientOutput();
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
