package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.*;
import com.example.service_to_booking_visit.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CalendarService calendarService;
    private final ClientService clientService;
    private final CompanyService companyService;

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

    private WorkingDay companyWorkingDay(Company company, DayOfWeek reservationDay) {
        return company.getWorkingDayList().stream()
                .filter(d -> d.getDay().equals(reservationDay))
                .findFirst().orElseThrow(() -> new RuntimeException("Company is not working at " + reservationDay));
    }



    public boolean isGivenTimeValid(Reservation reservation, Long companyId)  {
        LocalDateTime reservationDate = reservation.getDate();
        DayOfWeek reservationDay = reservationDate.getDayOfWeek();
        Company company = companyService.findById(companyId);

        WorkingDay workingDay = companyWorkingDay(company, reservationDay);

        String companyStartingHourInGivenDay = workingDay.getStartingHour();
        String companyEndingHourInGivenDay = workingDay.getEndingHour();

        LocalTime openingTime = LocalTime.parse(companyStartingHourInGivenDay);
        LocalTime closingTime = LocalTime.parse(companyEndingHourInGivenDay);
        LocalTime reservationTime = reservationDate.toLocalTime();

        return reservationTime.isAfter(openingTime) && reservationTime.isBefore(closingTime);
    }

    public void addReservationToClient(Long clientId, Long reservationId) {
        Client client = clientService.findById(clientId);
        client.getReservationList()
                .add(findById(reservationId));
        clientService.save(client);
    }

    public void bookVisit(Long clientId, Long reservationId, Long calendarId){
        addReservationToCalendar(calendarId, reservationId);
        addReservationToClient(clientId, reservationId);
    }

}