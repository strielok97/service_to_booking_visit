package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.Calendar;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Reservation;
import com.example.service_to_booking_visit.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ReservationServiceTest {
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    CalendarService calendarService;
    @InjectMocks
    ReservationService reservationService;

    Client client;
    Reservation reservation;
    Calendar calendar;

    @BeforeEach
    void setUp(){
        List<Reservation> reservationList = new ArrayList<>();
        reservation = new Reservation(5L, null, "Warszawa", null);
        calendar = new Calendar(1L, null, reservationList);
        client = new Client(1L, "Anna", "Nowak", 685745892, reservationList);
    }

    @Test
    void shouldAddReservationToClient(){
        // given
        Mockito.when(calendarService.findById(any())).thenReturn(calendar);
        Mockito.when(reservationRepository.findById(any())).thenReturn(Optional.of(reservation));
        int currentSize = client.getReservationList().size();
        //when
        reservationService.addReservationToClient(1L,5L);
        //then
        int result = client.getReservationList().size();
        assertEquals(currentSize+1, result);
    }

}