package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.SavingsGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;
import java.util.Optional;


public interface SavingsGroupRepository extends JpaRepository<SavingsGroups, UUID> {

    @Query("SELECT g FROM SavingsGroups g WHERE NOT EXISTS " +
           "(SELECT t FROM Treasurer t WHERE t.savingsGroup = g) " +
           "ORDER BY g.groupNumber")
    List<SavingsGroups> findFreeSavingsGroups();
    Optional<SavingsGroups> findByGroupNumber(int groupNumber);

}