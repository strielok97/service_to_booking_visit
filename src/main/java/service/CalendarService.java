package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import persistance.Calendar;
import persistance.Client;
import repository.CalendarRepository;

import javax.persistence.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public Calendar findById(Long id) {
        return calendarRepository.findById(id).orElseThrow();
    }

    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    public void deleteById(Long id) {
        calendarRepository.deleteById(id);
    }
}
