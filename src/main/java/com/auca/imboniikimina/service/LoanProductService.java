package com.auca.imboniikimina.service;

import com.auca.imboniikimina.model.LoanProduct;
import com.auca.imboniikimina.repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanProductService {

@Autowired
    private LoanProductRepository loanProductRepository;

    public String saveLoanProduct(LoanProduct loanProduct) {
        loanProductRepository.save(loanProduct);
        return "Loan product saved successfully";
    }

    public List<LoanProduct> getAllLoanProducts() {
        return loanProductRepository.findAll();
    }

    public Optional<LoanProduct> getLoanProductById(UUID id) {
        return loanProductRepository.findById(id);
    }

    public String updateLoanProduct(UUID id, LoanProduct newData) {
        Optional<LoanProduct> existing = loanProductRepository.findById(id);
        if (existing.isEmpty()) {
            return "The loan product with that id does not exist";
        }
        LoanProduct loanProduct = existing.get();
        loanProduct.setName(newData.getName());
        loanProductRepository.save(loanProduct);
        return "Loan product updated successfully";
    }

    public String deleteLoanProduct(UUID id) {
        if (!loanProductRepository.existsById(id)) {
            return "The loan product with that id does not exist";
        }
        loanProductRepository.deleteById(id);
        return "Loan product deleted successfully";
    }
    
}
