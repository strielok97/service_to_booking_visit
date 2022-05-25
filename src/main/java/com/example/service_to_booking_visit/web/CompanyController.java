package com.example.service_to_booking_visit.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<Page<Company>> getAll(@RequestParam int size, @RequestParam int page) {
        if (size == 0) {
            size = 25;
        }
        if (page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(companyService.findAll(pageable));
    }

    @GetMapping("/{city}/companies?size=X&page=Y")
    public ResponseEntity<Page<Company>> getAllByCity(@RequestParam int size, @RequestParam int page, @PathVariable String city) {
        if (size == 0) {
            size = 25;
        }
        if (page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(companyService.findAllByCity(city,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getId(@PathVariable Long id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
