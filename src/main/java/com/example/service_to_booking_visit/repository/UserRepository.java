package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findBySecondName (String secondName);
    Optional<User> findByUsername (String username);
}
