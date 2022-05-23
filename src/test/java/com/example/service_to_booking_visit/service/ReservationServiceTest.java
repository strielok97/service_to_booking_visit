package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Reservation;
import com.example.service_to_booking_visit.persistance.Service;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService reservationService;

//    @Test
//    void shouldAddReservationToClient() {
//        //given
//        Client client = new Client(1L, "Michael", "Smith", 668475892, new ArrayList<>());
//        Reservation reservation = new Reservation(1L, LocalDateTime.of(2022,06,06,15,00), new Service());
//        //when
//        reservationService.addReservationToClient(1L, reservation);
//        //then
//        assertTrue(client.getReservationList().contains(reservation));
//
//    }
}