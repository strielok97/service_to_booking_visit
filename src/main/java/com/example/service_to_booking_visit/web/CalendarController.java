package com.example.service_to_booking_visit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Calendar;
import persistance.Client;

import java.util.List;

public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    ResponseEntity<List<Calendar>> getAll() {
        return ResponseEntity.ok(calendarService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Calendar> getId(@PathVariable Long id){
        return ResponseEntity.ok(calendarService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Calendar calendar) {
        return ResponseEntity.ok(calendarService.save(calendar));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        return calendarService.deleteById(id);
    }
}
