package com.example.service_to_booking_visit.dto;

import com.example.service_to_booking_visit.persistance.User;
import com.example.service_to_booking_visit.persistance.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        Long userId = user.getId();
        List<String> roles = user
                .getRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());

        return new UserDTO(userId, roles);
    }
}


