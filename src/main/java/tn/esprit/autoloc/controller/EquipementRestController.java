package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Gestion Equipements")
@RestController
@RequiredArgsConstructor
@RequestMapping("/equipement")
public class EquipementRestController {

    private final IEquipementService equipementService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les équipements")
    @GetMapping("/retrieve-all-equipements")
    public List<Equipement> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @Operation(description = "Récupérer un équipement par son id")
    @GetMapping("/retrieve-equipement/{equipement-id}")
    public Equipement retrieveEquipement(@PathVariable("equipement-id") Long id) {
        return equipementService.retrieveEquipement(id);
    }

    @Operation(description = "Ajouter un équipement")
    @PostMapping("/add-equipement")
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @Operation(description = "Ajouter une liste : les équipements")
    @PostMapping("/add-equipements")
    public List<Equipement> addEquipements(@RequestBody List<Equipement> equipements) {
        return equipementService.addEquipements(equipements);
    }

    @Operation(description = "Modifier un équipement")
    @PutMapping("/modify-equipement")
    public Equipement modifyEquipement(@RequestBody Equipement equipement) {
        return equipementService.updateEquipement(equipement);
    }

    @Operation(description = "Supprimer un équipement")
    @DeleteMapping("/remove-equipement/{equipement-id}")
    public void removeEquipement(@PathVariable("equipement-id") Long id) {
        equipementService.removeEquipement(id);
    }
}
