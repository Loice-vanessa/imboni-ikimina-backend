package com.auca.imboniikimina.controller;

import com.auca.imboniikimina.model.Loan;
import com.auca.imboniikimina.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.auca.imboniikimina.model.LoanStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Loan loan) {
        String message = loanService.saveLoan(loan);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAll() {
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        if (loan.isPresent()) {
            return new ResponseEntity<>(loan.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The loan with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Loan loan) {
        String message = loanService.updateLoan(id, loan);
        if (message.equals("Loan updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = loanService.deleteLoan(id);
        if (message.equals("Loan deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

        @GetMapping("/by-status")
    public ResponseEntity<List<Loan>> getByStatus(@RequestParam LoanStatus status) {
        return new ResponseEntity<>(loanService.getLoansByStatus(status), HttpStatus.OK);
    }
}