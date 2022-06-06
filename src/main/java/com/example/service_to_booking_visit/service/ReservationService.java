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

    private final WorkingDayService workingDayService;

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

    public boolean doesCompanyWorksInGivenTime(Reservation reservation, Long companyId) {
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

    public boolean isReservationTimeAvailable(Reservation reservation, Long companyId) {
        LocalTime reservationStart = reservation.getDate().toLocalTime();
        Long timeDuration = Long.parseLong(reservation.getService().getDurationInMinutes());
        LocalTime reservationEnd = reservationStart
                .plusMinutes(timeDuration);

        WorkingDay reservationDayAtGivenCompany = getWorkingDay(reservation, companyId);

//        List<LocalTime> reservationTimesAtGivenDay = reservationDayAtGivenCompany.getReservationList()
//                .stream()
//                .map(t -> t.getDate())
//
//
//        for (LocalTime r : reservationTimesAtGivenDay) {
//            if (isGivenTermIsAvailable(reservationStart, reservationEnd, r)) {
//                return false;
//            }
//        }
        return true;
    }

    private boolean isGivenTermIsAvailable(LocalTime otherReservationStart, LocalTime otherReservationEnd, LocalTime myReservationTime) {
        return doesAnyReservationIncludeGivenReservation(otherReservationStart, otherReservationEnd, myReservationTime) ||
                isReservationBetweenStartAndEndAnyReservation(otherReservationStart, myReservationTime) ||
                isReservationEndBetweenStartAndEndAnyReservation(otherReservationEnd, myReservationTime);
    }

    private boolean doesAnyReservationIncludeGivenReservation(LocalTime reservationStart, LocalTime reservationEnd, LocalTime reservationTime) {
        return (reservationTime.isAfter(reservationStart) && reservationTime.isBefore(reservationEnd));
    }

    private boolean isReservationEndBetweenStartAndEndAnyReservation(LocalTime reservationEnd, LocalTime reservationTime) {
        return (reservationTime.isBefore(reservationEnd) && reservationTime.isAfter(reservationEnd));
    }

    private boolean isReservationBetweenStartAndEndAnyReservation(LocalTime reservationStart, LocalTime reservationTime) {
        return (reservationTime.isBefore(reservationStart) && reservationTime.isAfter(reservationStart));
    }

    private WorkingDay getWorkingDay(Reservation reservation, Long companyId) {
        WorkingDay reservationDayAtGivenCompany = companyService.findById(companyId).getCalendar().getWorkingDayList()
                .stream()
                .filter(t -> t.getDate().equals(reservation.getDate().toLocalDate()))
                .findAny()
                .orElseThrow();
        return reservationDayAtGivenCompany;
    }

    public void addReservationToClient(Long clientId, Long reservationId) {
        Client client = clientService.findById(clientId);
        client.getReservationList()
                .add(findById(reservationId));
        clientService.save(client);
    }

    public void bookVisit(Long clientId, Long reservationId, Long calendarId) {
        addReservationToCalendar(calendarId, reservationId);
        addReservationToClient(clientId, reservationId);
    }

}