package com.auca.imboniikimina.controller;
import com.auca.imboniikimina.model.SavingsGroups;
import com.auca.imboniikimina.service.SavingsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/savings-groups")
public class SavingsGroupController {
    @Autowired
    private SavingsGroupService savingsGroupService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SavingsGroups savingsGroup) {
        String message = savingsGroupService.saveSavingsGroup(savingsGroup);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<SavingsGroups>> getAll() {
        return new ResponseEntity<>(savingsGroupService.getAllSavingsGroups(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<SavingsGroups> savingsGroup =savingsGroupService.getSavingsGroupById(id);
        if (savingsGroup.isPresent()) { 
            return new ResponseEntity<>(savingsGroup.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The savings group with that id does not exist", HttpStatus.NOT_FOUND);
        } 
    }
        
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody SavingsGroups savingsGroup) {
        String message = savingsGroupService.updateSavingsGroup(id, savingsGroup);
        if (message.equals("Savings group updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
           return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
         }
    }
            
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = savingsGroupService.deleteSavingsGroup(id);
        if (message.equals("Savings group deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else { 
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND); 
        }
    }
 }