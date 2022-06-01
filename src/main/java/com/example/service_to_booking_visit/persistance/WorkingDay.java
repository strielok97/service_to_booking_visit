package com.example.service_to_booking_visit.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek day;
    private String startingHour;
    private String endingHour;

}
