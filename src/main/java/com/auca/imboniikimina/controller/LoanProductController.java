package com.auca.imboniikimina.controller;

import com.auca.imboniikimina.model.LoanProduct;
import com.auca.imboniikimina.service.LoanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/loan-products")
public class LoanProductController {
    
     @Autowired
    private LoanProductService loanProductService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LoanProduct loanProduct) {
        String message = loanProductService.saveLoanProduct(loanProduct);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanProduct>> getAll() {
        return new ResponseEntity<>(loanProductService.getAllLoanProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<LoanProduct> loanProduct = loanProductService.getLoanProductById(id);
        if (loanProduct.isPresent()) {
            return new ResponseEntity<>(loanProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The loan product with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody LoanProduct loanProduct) {
        String message = loanProductService.updateLoanProduct(id, loanProduct);
        if (message.equals("Loan product updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = loanProductService.deleteLoanProduct(id);
        if (message.equals("Loan product deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
