package com.revature.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Loan;
import com.revature.library.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService ls;

    public LoanController(LoanService ls) {
        this.ls = ls;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody @Valid Loan loan){
        Loan saved = ls.createLoan(loan.getBook().getId(), loan.getPatron().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id){
        Loan returned = ls.returnLoan(id);
        return ResponseEntity.ok(returned);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Loan>> getloansByPatronId(@PathVariable Long id){
        List<Loan> loan = ls.getLoansByPatron(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Loan>> getActiveLoans(){
        return ResponseEntity.ok(ls.getActiveLoans());
    }
}
