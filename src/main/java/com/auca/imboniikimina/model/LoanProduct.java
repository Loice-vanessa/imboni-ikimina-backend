package com.auca.imboniikimina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loan_product")
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "loanProducts")
    @JsonIgnore
    private List<Treasurer> treasurers = new ArrayList<>();


     public LoanProduct() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Treasurer> getTreasurers() { return treasurers; }
    public void setTreasurers(List<Treasurer> treasurers) { this.treasurers = treasurers; }
}
