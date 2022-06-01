package com.example.service_to_booking_visit.web;

import com.example.service_to_booking_visit.persistance.Service;
import com.example.service_to_booking_visit.persistance.WorkingDay;
import com.example.service_to_booking_visit.service.WorkingDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workingDays")
public class WorkingDayController {

    private final WorkingDayService workingDayService;

    @GetMapping
    ResponseEntity<List<WorkingDay>> getAll() {
        return ResponseEntity.ok(workingDayService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<WorkingDay> getId(@PathVariable Long id){
        return ResponseEntity.ok(workingDayService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WorkingDay> save(@RequestBody WorkingDay workingDay) {
        return ResponseEntity.ok(workingDayService.save(workingDay));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        workingDayService.deleteById(id);
    }
}
