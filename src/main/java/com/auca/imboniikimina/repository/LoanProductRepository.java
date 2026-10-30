package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LoanProductRepository extends JpaRepository<LoanProduct, UUID> {
    
}
