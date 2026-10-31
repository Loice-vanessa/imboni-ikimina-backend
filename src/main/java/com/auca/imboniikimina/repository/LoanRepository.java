package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByStatusOrderByRequestDateAsc(LoanStatus status);
}