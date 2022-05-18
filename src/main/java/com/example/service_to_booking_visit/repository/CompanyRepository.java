package com.example.service_to_booking_visit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service_to_booking_visit.persistance.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findAllByCity(String city, Pageable pageable);
}
