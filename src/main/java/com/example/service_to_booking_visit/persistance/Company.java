package com.example.service_to_booking_visit.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String city;
    private Long NIP;


    @OneToOne
    private Calendar calendar;

    @OneToMany
    private List<Service> serviceList;


}
