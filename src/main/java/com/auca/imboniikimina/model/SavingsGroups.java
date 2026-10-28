package com.auca.imboniikimina.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "savings_group")
public class SavingsGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int groupNumber;
    private String name;

    public SavingsGroups() {
    }

    public UUID getId() { return id;}
    public void setId(UUID id) { this.id = id; }
    
    public int getGroupNumber() { return groupNumber;}
    public void setGroupNumber(int groupNumber) { this.groupNumber =groupNumber;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
}
