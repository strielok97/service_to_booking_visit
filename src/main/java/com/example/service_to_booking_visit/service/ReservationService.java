package com.example.service_to_booking_visit.service;

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
        return reservationRepository.findById(id).orElseThrow();
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

    private void addReservationToCalendar(Long calendarId, Long reservationId) {
        calendarService.findById(calendarId).getReservationList().add(reservationRepository.getById(reservationId));
    }

    private void addReservationToClient(Long clientId, Long reservationId) {
        calendarService.findById(clientId).getReservationList().add(reservationRepository.getById(reservationId));
    }

    public Reservation bookVisit(Long clientId, Long reservationId, Long calendarId){
        addReservationToCalendar(calendarId, reservationId);
        addReservationToClient(clientId, reservationId);
        return save(reservationRepository.getById(reservationId));
    }

}