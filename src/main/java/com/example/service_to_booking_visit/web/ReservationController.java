package com.example.service_to_booking_visit.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Reservation;
import com.example.service_to_booking_visit.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Reservation> getId(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.save(reservation));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        reservationService.deleteById(id);
    }

    @PostMapping("/client/{clientId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addReservationToWorkingDay(@PathVariable Long clientId, Long workingDayId){
        reservationService.addReservationToClient(clientId, workingDayId);
    }

    @PostMapping("/client/{clientId}/company/{companyId}")
    public void bookVisit(@PathVariable Long clientId,@RequestBody Reservation myReservation,Long calendarId,@PathVariable Long companyId){
        reservationService.bookVisit(clientId, myReservation, calendarId, companyId);
    }
}
