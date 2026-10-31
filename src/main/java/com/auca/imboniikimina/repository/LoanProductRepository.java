package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.UUID;

public interface LoanProductRepository extends JpaRepository<LoanProduct, UUID> {
    
    @Query("SELECT p FROM LoanProduct p WHERE p.treasurers IS EMPTY")
    List<LoanProduct> findUnusedLoanProducts();
}
