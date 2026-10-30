package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Gestion Clients")
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientRestController {

    private final IClientService clientService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les clients")
    @GetMapping("/retrieve-all-clients")
    public List<Client> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @Operation(description = "Récupérer un client par son id")
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long id) {
        return clientService.retrieveClient(id);
    }

    @Operation(description = "Ajouter un client")
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @Operation(description = "Ajouter une liste : les clients")
    @PostMapping("/add-clients")
    public List<Client> addClients(@RequestBody List<Client> clients) {
        return clientService.addClients(clients);
    }

    @Operation(description = "Modifier un client")
    @PutMapping("/modify-client")
    public Client modifyClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @Operation(description = "Supprimer un client")
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long id) {
        clientService.removeClient(id);
    }
}
