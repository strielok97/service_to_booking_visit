package web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Client;
import persistance.Reservation;
import service.ReservationService;

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
}
