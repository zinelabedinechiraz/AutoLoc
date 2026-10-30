package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Gestion Maintenances")
@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les maintenances")
    @GetMapping("/retrieve-all-maintenances")
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @Operation(description = "Récupérer une maintenance par son id")
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public Maintenance retrieveMaintenance(@PathVariable("maintenance-id") Long id) {
        return maintenanceService.retrieveMaintenance(id);
    }

    @Operation(description = "Ajouter une maintenance")
    @PostMapping("/add-maintenance")
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    @Operation(description = "Ajouter une liste : les maintenances")
    @PostMapping("/add-maintenances")
    public List<Maintenance> addMaintenances(@RequestBody List<Maintenance> maintenances) {
        return maintenanceService.addMaintenances(maintenances);
    }

    @Operation(description = "Modifier une maintenance")
    @PutMapping("/modify-maintenance")
    public Maintenance modifyMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.updateMaintenance(maintenance);
    }

    @Operation(description = "Supprimer une maintenance")
    @DeleteMapping("/remove-maintenance/{maintenance-id}")
    public void removeMaintenance(@PathVariable("maintenance-id") Long id) {
        maintenanceService.removeMaintenance(id);
    }
}
