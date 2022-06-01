package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {
}
