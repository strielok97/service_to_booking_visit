package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.Calendar;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    Client client;
    ReservationService reservationService;
    Reservation reservation;
//    Calendar calendar;

    @BeforeEach
    void setUp(){
        List<Reservation> reservationList = new ArrayList<>();
        reservation = new Reservation(5L, null, "Warszawa", null);
//        calendar = new Calendar(1L, null, reservationList);
        client = new Client(1L, "Anna", "Nowak", 685745892, reservationList);
    }

    @Test
    void shouldAddReservationToClient(){
        // given
        int currentSize = client.getReservationList().size();
        //when
        reservationService.addReservationToClient(client.getId(), reservation.getId());
        //then
        int result = client.getReservationList().size();
        assertEquals(result,currentSize+1);
    }

}