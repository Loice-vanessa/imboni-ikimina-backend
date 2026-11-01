package com.auca.imboniikimina.repository;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface RepaymentRepository extends JpaRepository<Repayment, UUID> {

    // E2
    @Query("SELECT r FROM Repayment r WHERE r.loan.member.memberNumber = :memberNumber " +
           "ORDER BY r.paidDate DESC")
    List<Repayment> findByMemberNumber(@Param("memberNumber") String memberNumber);
     // E3
    boolean existsByLoan(Loan loan);
}