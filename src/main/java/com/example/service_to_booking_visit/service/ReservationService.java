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

    private WorkingDay companyWorkingDay(Company company, DayOfWeek reservationDay) {
        return company.getWorkingDayList().stream()
                .filter(d -> d.getDay().equals(reservationDay))
                .findFirst().orElseThrow(() -> new RuntimeException("Company is not working at " + reservationDay));
    }

    private WorkingDay getWorkingDay(Reservation reservation, Long companyId) {
        return companyService.findById(companyId).getCalendar().getWorkingDayList()
                .stream()
                .filter(t -> t.getDate().equals(reservation.getDate().toLocalDate()))
                .findAny()
                .orElseThrow();
    }

    public boolean doesCompanyWorkInGivenTime(Reservation reservation, Long companyId) {
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

    private LocalTime getTimeOfReservationEnd(Reservation reservation){
        String reservationDuration = reservation.getService().getDurationInMinutes();
        return reservation.getDate().toLocalTime().plusMinutes(Long.parseLong(reservationDuration));
    }

    public boolean isReservationTimeAvailable(Reservation myReservation, Long companyId) {
        LocalTime myReservationStart = myReservation.getDate().toLocalTime();
        LocalTime myReservationEnd = getTimeOfReservationEnd(myReservation);

        WorkingDay reservationDayAtGivenCompany = getWorkingDay(myReservation, companyId);

        List<Reservation> reservationListAtGivenDay = reservationDayAtGivenCompany.getReservationList();

        for (Reservation otherReservation : reservationListAtGivenDay) {
            LocalTime otherReservationStart = otherReservation.getDate().toLocalTime();
            LocalTime otherReservationEnd = getTimeOfReservationEnd(otherReservation);

            if (isGivenTermIsAvailable(otherReservationStart, otherReservationEnd, myReservationStart, myReservationEnd)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGivenTermIsAvailable(LocalTime otherReservationStart, LocalTime otherReservationEnd, LocalTime myReservationStart, LocalTime myReservationEnd) {
        return isOtherReservationBetweenMyReservationStartAndEnd(otherReservationStart, otherReservationEnd, myReservationStart, myReservationEnd) ||
                isMyReservationStartBetweenStartAndEndOtherReservation(otherReservationStart, otherReservationEnd, myReservationStart) ||
                isMyReservationEndBetweenStartAndEndOtherReservation(otherReservationStart, otherReservationEnd, myReservationEnd);
    }

    private boolean isOtherReservationBetweenMyReservationStartAndEnd(LocalTime otherReservationStart, LocalTime otherReservationEnd, LocalTime myReservationStart, LocalTime myReservationEnd) {
        return (myReservationStart.isBefore(otherReservationStart) && myReservationEnd.isAfter(otherReservationEnd));
    }

    private boolean isMyReservationEndBetweenStartAndEndOtherReservation(LocalTime otherReservationStart, LocalTime otherReservationEnd, LocalTime myReservationEnd) {
        return (myReservationEnd.isBefore(otherReservationEnd) && myReservationEnd.isAfter(otherReservationStart));
    }

    private boolean isMyReservationStartBetweenStartAndEndOtherReservation(LocalTime otherReservationStart, LocalTime otherReservationEnd, LocalTime myReservationStart) {
        return (myReservationStart.isBefore(otherReservationEnd) && myReservationStart.isAfter(otherReservationStart));
    }

    public void addReservationToClient(Long clientId, Long reservationId) {
        Client client = clientService.findById(clientId);
        client.getReservationList()
                .add(findById(reservationId));
        clientService.save(client);
    }

    public void addReservationToWorkingDay(Long workingDayId, Long reservationId) {
        WorkingDay workingDay = workingDayService.findById(workingDayId);
        workingDay.getReservationList().add(findById(reservationId));
        workingDayService.save(workingDay);
    }

    private boolean isBookingAvaible(Reservation myReservation, Long companyId){
        boolean avaibility = doesCompanyWorkInGivenTime(myReservation, companyId) && isReservationTimeAvailable(myReservation, companyId);
        if (avaibility){
            save(myReservation);
        }
        return avaibility;
    }


    public void bookVisit(Long clientId, Reservation myReservation, Long calendarId, Long companyId) {
        if (isBookingAvaible(myReservation,companyId)){
            addReservationToWorkingDay(calendarId, myReservation.getId());
            addReservationToClient(clientId, myReservation.getId());
        }
    }

}