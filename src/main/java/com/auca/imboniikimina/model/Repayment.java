package com.auca.imboniikimina.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "repayment")
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String paymentChannel;
    private String note;
    private int amount;
    private LocalDate paidDate;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public Repayment() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPaymentChannel() { return paymentChannel; }
    public void setPaymentChannel(String paymentChannel) { this.paymentChannel = paymentChannel; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public LocalDate getPaidDate() { return paidDate; }
    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }

    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }
}