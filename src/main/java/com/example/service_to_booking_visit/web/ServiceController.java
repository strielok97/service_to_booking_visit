package com.example.service_to_booking_visit.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Service;
import com.example.service_to_booking_visit.service.ServiceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
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
    public ResponseEntity<Service> save(@RequestBody Service service) {
        return ResponseEntity.ok(serviceService.save(service));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        serviceService.deleteById(id);
    }
}
