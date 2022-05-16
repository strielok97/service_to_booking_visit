package com.example.service_to_booking_visit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;
import persistance.Service;

import java.util.List;

public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping
    ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Service> getId(@PathVariable Long id){
        return ResponseEntity.ok(serviceService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Service service) {
        return ResponseEntity.ok(serviceService.save(service));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        return serviceService.serviceById(id);
    }
}
