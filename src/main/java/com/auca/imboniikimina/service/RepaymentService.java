package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.model.LoanStatus;
import com.auca.imboniikimina.model.Repayment;
import com.auca.imboniikimina.repository.LoanRepository;
import com.auca.imboniikimina.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    // E1
    public String saveRepayment(UUID loanId, Repayment repayment) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = loanOpt.get();
        if (loan.getStatus() != LoanStatus.DISBURSED) {
            return "Repayments are only for DISBURSED loans";
        }
        repayment.setPaidDate(LocalDate.now());
        repayment.setLoan(loan);
        repaymentRepository.save(repayment);
        return "Repayment saved successfully";
    }

    public List<Repayment> getAllRepayments() {
        return repaymentRepository.findAll();
    }

    // E2
    public List<Repayment> getRepaymentsOfMember(String memberNumber) {
        return repaymentRepository.findByMemberNumber(memberNumber);
    }

    // E3
    public String deleteRepayment(UUID id) {
        if (!repaymentRepository.existsById(id)) {
            return "The repayment with that id does not exist";
        }
        repaymentRepository.deleteById(id);
        return "Repayment deleted successfully";
    }
}