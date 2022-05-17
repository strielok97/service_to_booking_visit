package com.example.service_to_booking_visit.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    ResponseEntity<List<Company>> getAll() {
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
        companyService.deleteById(id);
    }
}
