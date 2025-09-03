package com.api.water_sytem_management_java.controllers;


import com.api.water_sytem_management_java.controllers.dtos.ClientInput;
import com.api.water_sytem_management_java.models.Client;
import com.api.water_sytem_management_java.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/customers")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientOutput>> getAllClients() {
        List<ClientOutput> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientInput clientInput) {
        Client client = clientInput.toClient();
        Client savedClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientOutput> updateClient(@PathVariable UUID id, @RequestBody ClientInput clientInput) {
        Optional<ClientOutput> updatedClient = clientService.updateClient(id, clientInput);
        return updatedClient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
