package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.LoanProduct;
import com.auca.imboniikimina.model.SavingsGroups;
import com.auca.imboniikimina.model.Treasurer;
import com.auca.imboniikimina.repository.LoanProductRepository;
import com.auca.imboniikimina.repository.SavingsGroupRepository;
import com.auca.imboniikimina.repository.TreasurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreasurerService {

    @Autowired
    private TreasurerRepository treasurerRepository;

    @Autowired
    private LoanProductRepository loanProductRepository;

    @Autowired
    private SavingsGroupRepository savingsGroupRepository;

    public String saveTreasurer(Treasurer treasurer) {
        treasurerRepository.save(treasurer);
        return "Treasurer saved successfully";
    }

    public List<Treasurer> getAllTreasurers() {
        return treasurerRepository.findAll();
    }

    public Optional<Treasurer> getTreasurerById(UUID id) {
        return treasurerRepository.findById(id);
    }

    public String updateTreasurer(UUID id, Treasurer newData) {
        Optional<Treasurer> existing = treasurerRepository.findById(id);
        if (existing.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Treasurer treasurer = existing.get();
        treasurer.setFirstName(newData.getFirstName());
        treasurer.setLastName(newData.getLastName());
        treasurer.setPhone(newData.getPhone());
        treasurerRepository.save(treasurer);
        return "Treasurer updated successfully";
    }

    public String deleteTreasurer(UUID id) {
        if (!treasurerRepository.existsById(id)) {
            return "The treasurer with that id does not exist";
        }
        treasurerRepository.deleteById(id);
        return "Treasurer deleted successfully";
    }

    // C1
    public List<Treasurer> getTreasurersByLoanProduct(String name) {
        return treasurerRepository.findByLoanProductName(name);
    }

    // D3
    @Transactional
    public String addLoanProduct(UUID treasurerId, UUID loanProductId) {
        Optional<Treasurer> treasurerOpt = treasurerRepository.findById(treasurerId);
        if (treasurerOpt.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<LoanProduct> productOpt = loanProductRepository.findById(loanProductId);
        if (productOpt.isEmpty()) {
            return "That loan product does not exist";
        }
        Treasurer treasurer = treasurerOpt.get();
        LoanProduct loanProduct = productOpt.get();

        if (treasurer.getLoanProducts().contains(loanProduct)) {
            return "The treasurer already has that loan product";
        }
        treasurer.getLoanProducts().add(loanProduct);
        treasurerRepository.save(treasurer);
        return "Loan product added to the treasurer";
    }

    // D4
    public String changeSavingsGroup(UUID treasurerId, int groupNumber) {
        Optional<Treasurer> treasurerOpt = treasurerRepository.findById(treasurerId);
        if (treasurerOpt.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<SavingsGroups> groupOpt = savingsGroupRepository.findByGroupNumber(groupNumber);
        if (groupOpt.isEmpty()) {
            return "The savings group with that groupNumber does not exist";
        }
        SavingsGroups newGroup = groupOpt.get();
        if (treasurerRepository.existsBySavingsGroup(newGroup)) {
            return "That savings group is taken by another treasurer";
        }
        Treasurer treasurer = treasurerOpt.get();
        treasurer.setSavingsGroup(newGroup);
        treasurerRepository.save(treasurer);
        return "Savings group changed successfully";
    }
}