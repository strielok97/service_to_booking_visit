package com.example.service_to_booking_visit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;
import persistance.Company;

public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    ResponseEntity<List<getAll>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Company> getId(@PathVariable Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        return companyService.deleteById(id);
    }
}
