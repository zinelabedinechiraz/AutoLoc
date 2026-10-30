package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Gestion Paiements")
@RestController
@RequiredArgsConstructor
@RequestMapping("/paiement")
public class PaiementRestController {

    private final IPaiementService paiementService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les paiements")
    @GetMapping("/retrieve-all-paiements")
    public List<Paiement> retrieveAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @Operation(description = "Récupérer un paiement par son id")
    @GetMapping("/retrieve-paiement/{paiement-id}")
    public Paiement retrievePaiement(@PathVariable("paiement-id") Long id) {
        return paiementService.retrievePaiement(id);
    }

    @Operation(description = "Ajouter un paiement")
    @PostMapping("/add-paiement")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @Operation(description = "Ajouter une liste : les paiements")
    @PostMapping("/add-paiements")
    public List<Paiement> addPaiements(@RequestBody List<Paiement> paiements) {
        return paiementService.addPaiements(paiements);
    }

    @Operation(description = "Modifier un paiement")
    @PutMapping("/modify-paiement")
    public Paiement modifyPaiement(@RequestBody Paiement paiement) {
        return paiementService.updatePaiement(paiement);
    }

    @Operation(description = "Supprimer un paiement")
    @DeleteMapping("/remove-paiement/{paiement-id}")
    public void removePaiement(@PathVariable("paiement-id") Long id) {
        paiementService.removePaiement(id);
    }
}
