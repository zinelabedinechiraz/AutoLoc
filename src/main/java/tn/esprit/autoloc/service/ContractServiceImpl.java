package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements IContractService{
    final ContractRepository CR1;
    @Override
    public List<Contrat> retrieveAllContracts() {
        return (List<Contrat>) CR1.findAll();
    }

    @Override
    public Contrat addContract(Contrat c1) {
        return CR1.save(c1);
    }

    @Override
    public Contrat updateContract(Contrat c1) {
        return CR1.save(c1);
    }

    @Override
    public Contrat retrieveContract(Long idContract) {
        return CR1.findById(idContract).orElse(null);
    }

    @Override
    public void removeContract(Long idContract) {
        CR1.deleteById(idContract);

    }

    @Override
    public List<Contrat> addContracts(List<Contrat> contracts) {
        return (List<Contrat>) CR1.saveAll(contracts);
    }
}
