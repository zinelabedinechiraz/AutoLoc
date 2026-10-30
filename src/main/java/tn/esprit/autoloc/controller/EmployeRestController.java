package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Gestion Employés")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employe")
public class EmployeRestController {

    private final IEmployeService employeService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les employés")
    @GetMapping("/retrieve-all-employes")
    public List<Employe> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @Operation(description = "Récupérer un employé par son id")
    @GetMapping("/retrieve-employe/{employe-id}")
    public Employe retrieveEmploye(@PathVariable("employe-id") Long id) {
        return employeService.retrieveEmploye(id);
    }

    @Operation(description = "Ajouter un employé")
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    @Operation(description = "Ajouter une liste : les employés")
    @PostMapping("/add-employes")
    public List<Employe> addEmployes(@RequestBody List<Employe> employes) {
        return employeService.addEmployes(employes);
    }

    @Operation(description = "Modifier un employé")
    @PutMapping("/modify-employe")
    public Employe modifyEmploye(@RequestBody Employe employe) {
        return employeService.updateEmploye(employe);
    }

    @Operation(description = "Supprimer un employé")
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long id) {
        employeService.removeEmploye(id);
    }

    // ===================== Scenario 1 : mutation d'un agent =====================

    @Operation(description = "Désaffecter un employé de son agence (1ère étape d'une mutation)")
    @PutMapping("/desaffecter-employe/{employe-id}")
    public Employe desaffecterEmploye(@PathVariable("employe-id") Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }

    @Operation(description = "Affecter un employé (sans agence) à une agence (2ème étape d'une mutation)")
    @PutMapping("/affecter-employe/{employe-id}/{agence-id}")
    public Employe affecterEmploye(@PathVariable("employe-id") Long idEmploye,
                                   @PathVariable("agence-id") Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}
