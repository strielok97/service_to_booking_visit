package com.example.service_to_booking_visit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;
import persistance.Reservation;

public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    ResponseEntity<List<getAll>> getAll() {
        return ResponseEntity.ok(reservationService.find());
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getId(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.save(reservation));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        return reservationService.deleteById(id);
    }
}
