package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.SavingsGroups;
import com.auca.imboniikimina.model.Treasurer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface TreasurerRepository extends JpaRepository<Treasurer, UUID> {

    @Query("SELECT DISTINCT t FROM Treasurer t JOIN t.loanProducts p " +
           "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Treasurer> findByLoanProductName(@Param("name") String name);
    boolean existsBySavingsGroup(SavingsGroups savingsGroup);

}