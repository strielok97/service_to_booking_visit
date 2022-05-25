package com.example.service_to_booking_visit.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/servicesAsc?size=X&page=Y")
    public ResponseEntity<Page<Service>> sortServicesByPriceAsc(@RequestParam int size, @RequestParam int page) {
        if (size == 0) {
            size = 25;
        }
        if (page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(serviceService.getServicesByPriceAsc(pageable));
    }

    @GetMapping("/servicesDesc?size=X&page=Y")
    public ResponseEntity<Page<Service>> sortServicesByPriceDesc(@RequestParam int size, @RequestParam int page) {
        if (size == 0) {
            size = 25;
        }
        if (page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(serviceService.getServicesByPriceDesc(pageable));
    }

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
