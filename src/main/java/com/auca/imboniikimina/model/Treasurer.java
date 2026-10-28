package com.auca.imboniikimina.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treasurer")
public class Treasurer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "savings_group_id")
    private SavingsGroups savingsGroup;

    @ManyToMany
    @JoinTable(
        name = "treasurer_loan_product",
        joinColumns = @JoinColumn(name = "treasurer_id"),
        inverseJoinColumns = @JoinColumn(name = "loan_product_id")
    )
    private List<LoanProduct> loanProducts = new ArrayList<>();

    public Treasurer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SavingsGroups getSavingsGroup() {
        return savingsGroup;
    }

    public void setSavingsGroup(SavingsGroups savingsGroup) {
        this.savingsGroup = savingsGroup;
    }

    public List<LoanProduct> getLoanProducts() {
        return loanProducts;
    }

    public void setLoanProducts(List<LoanProduct> loanProducts) {
        this.loanProducts = loanProducts;
    }
    
}
