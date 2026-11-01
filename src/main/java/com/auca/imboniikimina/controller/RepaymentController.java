package com.auca.imboniikimina.controller;

import com.auca.imboniikimina.model.Repayment;
import com.auca.imboniikimina.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID loanId, @RequestBody Repayment repayment) {
        String message = repaymentService.saveRepayment(loanId, repayment);
        if (message.equals("Repayment saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The loan with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Repayment>> getAll() {
        return new ResponseEntity<>(repaymentService.getAllRepayments(), HttpStatus.OK);
    }
}