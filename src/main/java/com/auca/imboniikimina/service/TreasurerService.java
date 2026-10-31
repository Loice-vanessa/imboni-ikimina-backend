package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.Treasurer;
import com.auca.imboniikimina.repository.TreasurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreasurerService {

    @Autowired
    private TreasurerRepository treasurerRepository;

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
        public List<Treasurer> getTreasurersByLoanProduct(String name) {
        return treasurerRepository.findByLoanProductName(name);
    }
}