package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContractService;

import java.util.List;

@Tag(name = "Gestion Contrats")
@RestController
@RequiredArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

    private final IContractService contractService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les contrats")
    @GetMapping("/retrieve-all-contrats")
    public List<Contrat> retrieveAllContracts() {
        return contractService.retrieveAllContracts();
    }

    @Operation(description = "Récupérer un contrat par son id")
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public Contrat retrieveContract(@PathVariable("contrat-id") Long id) {
        return contractService.retrieveContract(id);
    }

    @Operation(description = "Ajouter un contrat")
    @PostMapping("/add-contrat")
    public Contrat addContract(@RequestBody Contrat contrat) {
        return contractService.addContract(contrat);
    }

    @Operation(description = "Ajouter une liste : les contrats")
    @PostMapping("/add-contrats")
    public List<Contrat> addContracts(@RequestBody List<Contrat> contrats) {
        return contractService.addContracts(contrats);
    }

    @Operation(description = "Modifier un contrat")
    @PutMapping("/modify-contrat")
    public Contrat modifyContract(@RequestBody Contrat contrat) {
        return contractService.updateContract(contrat);
    }

    @Operation(description = "Supprimer un contrat")
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContract(@PathVariable("contrat-id") Long id) {
        contractService.removeContract(id);
    }
}
