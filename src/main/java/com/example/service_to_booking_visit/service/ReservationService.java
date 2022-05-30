package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Reservation;
import com.example.service_to_booking_visit.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CalendarService calendarService;

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    public void addReservationToCalendar(Long calendarId, Long reservationId) {
        Calendar calendar = calendarService.findById(calendarId);
                calendar.getReservationList()
                .add(findById(reservationId));
        calendarService.save(calendar);
    }

    public void addReservationToClient(Long calendarId, Long reservationId) {
        Calendar calendar = calendarService.findById(calendarId);
        calendar.getReservationList()
                .add(findById(reservationId));
        calendarService.save(calendar);
    }

    public void bookVisit(Long clientId, Long reservationId, Long calendarId){
        addReservationToCalendar(calendarId, reservationId);
        addReservationToClient(clientId, reservationId);
    }

}