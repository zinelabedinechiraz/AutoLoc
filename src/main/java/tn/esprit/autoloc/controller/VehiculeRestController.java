package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Gestion Véhicules")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les véhicules")
    @GetMapping("/retrieve-all-vehicules")
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    @Operation(description = "Récupérer un véhicule par son id")
    @GetMapping("/retrieve-vehicule/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long id) {
        return vehiculeService.retrieveVehicule(id);
    }

    @Operation(description = "Ajouter un véhicule")
    @PostMapping("/add-vehicule")
    public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.addVehicule(vehicule);
    }

    @Operation(description = "Ajouter une liste : les véhicules")
    @PostMapping("/add-vehicules")
    public List<Vehicule> addVehicules(@RequestBody List<Vehicule> vehicules) {
        return vehiculeService.addVehicules(vehicules);
    }

    @Operation(description = "Modifier un véhicule")
    @PutMapping("/modify-vehicule")
    public Vehicule modifyVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.updateVehicule(vehicule);
    }

    @Operation(description = "Supprimer un véhicule")
    @DeleteMapping("/remove-vehicule/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long id) {
        vehiculeService.removeVehicule(id);
    }

    // ===================== Scenario 2 : vehicule neuf rattache a une agence =====================

    @Operation(description = "Rattacher un véhicule à une agence")
    @PutMapping("/affecter-vehicule/{vehicule-id}/{agence-id}")
    public Vehicule affecterVehicule(@PathVariable("vehicule-id") Long idVehicule,
                                     @PathVariable("agence-id") Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    // ===================== Scenario 3 : siege bebe installe puis retire =====================

    @Operation(description = "Installer un équipement sur un véhicule")
    @PutMapping("/affecter-equipement/{vehicule-id}/{equipement-id}")
    public Vehicule affecterEquipement(@PathVariable("vehicule-id") Long idVehicule,
                                       @PathVariable("equipement-id") Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @Operation(description = "Retirer un équipement d'un véhicule")
    @PutMapping("/desaffecter-equipement/{vehicule-id}/{equipement-id}")
    public Vehicule desaffecterEquipement(@PathVariable("vehicule-id") Long idVehicule,
                                          @PathVariable("equipement-id") Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}
