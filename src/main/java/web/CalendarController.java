package web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistance.Calendar;
import persistance.Client;
import service.CalendarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendars")
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
    public ResponseEntity<Calendar> save(@RequestBody Calendar calendar) {
        return ResponseEntity.ok(calendarService.save(calendar));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        calendarService.deleteById(id);
    }
}
