package com.example.service_to_booking_visit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;

public class ClientController {

    private final ClientService clientService;

    @GetMapping
    ResponseEntity<List<getAll>> getAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getId(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        return clientService.deleteById(id);
    }

}
