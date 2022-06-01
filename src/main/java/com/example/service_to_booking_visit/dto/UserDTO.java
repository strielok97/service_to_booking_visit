package com.example.service_to_booking_visit.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private List<String> roles;


    public Long getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }

}
