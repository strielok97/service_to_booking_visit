package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
