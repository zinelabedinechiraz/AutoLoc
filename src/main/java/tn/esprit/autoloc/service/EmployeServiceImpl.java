package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService{
    final EmployeRepository ER;
    final AgenceRepository agenceRepository;   // necessaire pour les affectations

    @Override
    public List<Employe> retrieveAllEmployes() {
        return (List<Employe>) ER.findAll();
    }

    @Override
    public Employe addEmploye(Employe e) {
        return ER.save(e);
    }

    @Override
    public Employe updateEmploye(Employe e) {
        return ER.save(e);
    }

    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return ER.findById(idEmploye).orElse(null);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        ER.deleteById(idEmploye);

    }

    @Override
    public List<Employe> addEmployes(List<Employe> employes) {
        return (List<Employe>) ER.saveAll(employes);
    }

    // ==========================================================================
    // Scenario 1 : mutation d'un agent vers une autre agence (ex. Tunis)
    // Relation ManyToOne : Employe (proprietaire, contient id_agence) -> Agence
    // => l'affectation et la desaffectation se font sur l'objet Employe.
    // Un employe n'appartient qu'a UNE agence : un transfert = desaffecter puis affecter.
    // ==========================================================================

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = ER.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable : id = " + idEmploye));

        employe.setAgence(null);          // on casse le lien : id_agence = NULL en base
        return ER.save(employe);          // l'employe existe toujours, seul le lien est supprime
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = ER.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable : id = " + idEmploye));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : id = " + idAgence));

        // Regle metier : un employe ne peut appartenir qu'a une seule agence a la fois
        if (employe.getAgence() != null) {
            throw new IllegalStateException("L'employe " + idEmploye + " appartient deja a l'agence "
                    + employe.getAgence().getNom() + " : il faut d'abord le desaffecter.");
        }

        employe.setAgence(agence);        // affectation sur le cote proprietaire (Employe)
        return ER.save(employe);
    }
}
