package com.auca.imboniikimina.controller;

import com.auca.imboniikimina.model.Treasurer;
import com.auca.imboniikimina.service.TreasurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/treasurers")
public class TreasurerController {

    @Autowired
    private TreasurerService treasurerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Treasurer treasurer) {
        String message = treasurerService.saveTreasurer(treasurer);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Treasurer>> getAll() {
        return new ResponseEntity<>(treasurerService.getAllTreasurers(), HttpStatus.OK);
    }

    // C1
    @GetMapping("/by-loan-product")
    public ResponseEntity<List<Treasurer>> getByLoanProduct(@RequestParam String name) {
        return new ResponseEntity<>(treasurerService.getTreasurersByLoanProduct(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Treasurer> treasurer = treasurerService.getTreasurerById(id);
        if (treasurer.isPresent()) {
            return new ResponseEntity<>(treasurer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The treasurer with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Treasurer treasurer) {
        String message = treasurerService.updateTreasurer(id, treasurer);
        if (message.equals("Treasurer updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = treasurerService.deleteTreasurer(id);
        if (message.equals("Treasurer deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // D3
    @PutMapping("/{treasurerId}/loan-products/{loanProductId}")
    public ResponseEntity<String> addLoanProduct(@PathVariable UUID treasurerId, @PathVariable UUID loanProductId) {
        String message = treasurerService.addLoanProduct(treasurerId, loanProductId);
        if (message.equals("Loan product added to the treasurer")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The treasurer already has that loan product")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

        // D4
    @PatchMapping("/{treasurerId}/savings-group")
    public ResponseEntity<String> changeSavingsGroup(@PathVariable UUID treasurerId, @RequestParam int groupNumber) {
        String message = treasurerService.changeSavingsGroup(treasurerId, groupNumber);
        if (message.equals("Savings group changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That savings group is taken by another treasurer")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    
}