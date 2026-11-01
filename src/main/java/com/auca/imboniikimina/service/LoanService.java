package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.model.LoanStatus;
import com.auca.imboniikimina.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // A2 + D1
    public String saveLoan(Loan loan) {
        if (loan.getRequestDate().isBefore(LocalDate.now())) {
            return "Loan date cannot be in the past";
        }
        boolean busy = loanRepository.existsByTreasurerIdAndRequestDateAndStatusNot(
                loan.getTreasurer().getId(), loan.getRequestDate(), LoanStatus.REJECTED);
        if (busy) {
            return "Treasurer already has a loan on that date";
        }
        loan.setStatus(LoanStatus.REQUESTED);
        loanRepository.save(loan);
        return "Loan saved successfully";
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(UUID id) {
        return loanRepository.findById(id);
    }

    public String updateLoan(UUID id, Loan newData) {
        Optional<Loan> existing = loanRepository.findById(id);
        if (existing.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = existing.get();
        loan.setRequestDate(newData.getRequestDate());
        loan.setAmount(newData.getAmount());
        loan.setPurpose(newData.getPurpose());
        loan.setMember(newData.getMember());
        loan.setTreasurer(newData.getTreasurer());
        // status is NOT changed here: it only changes through PATCH /api/loans/{id}/status (D2)
        loanRepository.save(loan);
        return "Loan updated successfully";
    }

    public String deleteLoan(UUID id) {
        if (!loanRepository.existsById(id)) {
            return "The loan with that id does not exist";
        }
        loanRepository.deleteById(id);
        return "Loan deleted successfully";
    }

    // B2
    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatusOrderByRequestDateAsc(status);
    }

    // B3
    public List<Loan> getLoansBetween(LocalDate start, LocalDate end) {
        return loanRepository.findByRequestDateBetweenOrderByRequestDateAsc(start, end);
    }
}