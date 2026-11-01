package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    // B2
    List<Loan> findByStatusOrderByRequestDateAsc(LoanStatus status);

    // B3
    List<Loan> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    // D1
    boolean existsByTreasurerIdAndRequestDateAndStatusNot(UUID treasurerId, LocalDate requestDate, LoanStatus status);

    // F1
    @Query("SELECT l.status, COUNT(l) FROM Loan l GROUP BY l.status")
    List<Object[]> countLoansByStatus();
}