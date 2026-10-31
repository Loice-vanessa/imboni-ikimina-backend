package com.auca.imboniikimina.repository;
import com.auca.imboniikimina.model.SavingsGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface SavingsGroupRepository extends JpaRepository<SavingsGroups, UUID> {
    
}