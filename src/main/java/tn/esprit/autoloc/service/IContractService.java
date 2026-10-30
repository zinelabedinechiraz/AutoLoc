package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContractService {
    List<Contrat> retrieveAllContracts();
    Contrat addContract(Contrat c1);
    Contrat updateContract(Contrat c1);
    Contrat retrieveContract(Long idContract);
    void removeContract(Long idContract);
    List<Contrat> addContracts (List<Contrat> contracts);
}
