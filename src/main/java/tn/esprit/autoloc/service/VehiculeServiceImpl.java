package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService{
    final VehiculeRepository VR;
    final AgenceRepository agenceRepository;         // necessaire pour affecterVehicule
    final EquipementRepository equipementRepository; // necessaire pour les equipements

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return (List<Vehicule>) VR.findAll();
    }

    @Override
    public Vehicule addVehicule(Vehicule v) {
        return VR.save(v);
    }

    @Override
    public Vehicule updateVehicule(Vehicule v) {
        return VR.save(v);
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return VR.findById(idVehicule).orElse(null);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        VR.deleteById(idVehicule);

    }

    @Override
    public List<Vehicule> addVehicules(List<Vehicule> vehicules) {
        return (List<Vehicule>) VR.saveAll(vehicules);
    }

    // ==========================================================================
    // Scenario 2 : un vehicule neuf arrive et est rattache a l'agence de Sfax
    // Relation ManyToOne : Vehicule (proprietaire, contient id_agence) -> Agence
    // => l'affectation se fait sur l'objet Vehicule.
    // ==========================================================================

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findVehicule(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : id = " + idAgence));

        vehicule.setAgence(agence);       // remplit la colonne vehicule.id_agence
        return VR.save(vehicule);
    }

    // ==========================================================================
    // Scenario 3 : un client demande un siege bebe ; l'agent l'installe sur le
    // vehicule puis le retire au retour.
    // Relation ManyToMany : Vehicule est le PROPRIETAIRE (il a le @JoinTable,
    // Equipement a mappedBy) => on ajoute/retire dans vehicule.getEquipements().
    // ==========================================================================

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findVehicule(idVehicule);
        Equipement equipement = findEquipement(idEquipement);

        vehicule.getEquipements().add(equipement);    // INSERT dans vehicule_equipement
        return VR.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findVehicule(idVehicule);
        Equipement equipement = findEquipement(idEquipement);

        if (!vehicule.getEquipements().contains(equipement)) {
            throw new IllegalStateException("L'equipement " + idEquipement
                    + " n'est pas installe sur le vehicule " + idVehicule);
        }

        vehicule.getEquipements().remove(equipement); // DELETE de la ligne de jointure
        return VR.save(vehicule);                     // l'equipement, lui, n'est PAS supprime
    }

    // ----- methodes utilitaires -----
    private Vehicule findVehicule(Long idVehicule) {
        return VR.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : id = " + idVehicule));
    }

    private Equipement findEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : id = " + idEquipement));
    }
}
