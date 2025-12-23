package com.revature.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Patron;
import com.revature.library.service.PatronService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService pa;

    public PatronController(PatronService pa){
        this.pa=pa;
    }

    @GetMapping
    public List<Patron> getAllPatrons(){
        return pa.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id){
        Optional<Patron> patron = pa.findById(id);
        if(patron.isPresent()){
            return ResponseEntity.ok(patron.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody @Valid Patron pat){
        Patron saved = pa.addPatron(pat);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
