package com.example.service_to_booking_visit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service_to_booking_visit.persistance.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
