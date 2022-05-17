package com.example.service_to_booking_visit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service_to_booking_visit.persistance.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
