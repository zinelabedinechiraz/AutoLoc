package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Gestion Agences")
@RestController
@RequiredArgsConstructor
@RequestMapping("/agence")
public class AgenceRestController {

    private final IAgenceService agenceService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les agences")
    @GetMapping("/retrieve-all-agences")
    public List<Agence> retrieveAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @Operation(description = "Récupérer une agence par son id")
    @GetMapping("/retrieve-agence/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long id) {
        return agenceService.retrieveAgence(id);
    }

    @Operation(description = "Ajouter une agence")
    @PostMapping("/add-agence")
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    @Operation(description = "Ajouter une liste : les agences")
    @PostMapping("/add-agences")
    public List<Agence> addAgences(@RequestBody List<Agence> agences) {
        return agenceService.addAgences(agences);
    }

    @Operation(description = "Modifier une agence")
    @PutMapping("/modify-agence")
    public Agence modifyAgence(@RequestBody Agence agence) {
        return agenceService.updateAgence(agence);
    }

    @Operation(description = "Supprimer une agence")
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long id) {
        agenceService.removeAgence(id);
    }
}
