package com.auca.imboniikimina.service;
import com.auca.imboniikimina.model.SavingsGroups;
import com.auca.imboniikimina.repository.SavingsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class SavingsGroupService {
        @Autowired
        private SavingsGroupRepository savingsGroupRepository;
        public String saveSavingsGroup(SavingsGroups savingsGroup) {
            savingsGroupRepository.save(savingsGroup);
            return "Savings group saved successfully";
        }

        public List<SavingsGroups> getAllSavingsGroups() {
            return savingsGroupRepository.findAll();
        }

        public Optional<SavingsGroups> getSavingsGroupById(UUID id) {
            return savingsGroupRepository.findById(id);
        }
        public String updateSavingsGroup(UUID id, SavingsGroups newData) {
            Optional<SavingsGroups> existing = savingsGroupRepository.findById(id);
            if (existing.isEmpty()) {
                return "The savings group with that id does not exist";
            }
            SavingsGroups savingsGroup = existing.get();
            savingsGroup.setGroupNumber(newData.getGroupNumber());
            savingsGroup.setName(newData.getName());
            savingsGroupRepository.save(savingsGroup);
            return "Savings group updated successfully";
        }

        public String deleteSavingsGroup(UUID id) {
             if (!savingsGroupRepository.existsById(id)) {
                return "The savings group with that id does not exist";
            }
            savingsGroupRepository.deleteById(id);
            return "Savings group deleted successfully";
        }

        public List<SavingsGroups> getFreeSavingsGroups() {
        return savingsGroupRepository.findFreeSavingsGroups();
    }

}