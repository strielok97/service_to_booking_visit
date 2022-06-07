package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.WorkingDay;
import com.example.service_to_booking_visit.repository.WorkingDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingDayService {

    private final WorkingDayRepository workingDayRepository;
    private final CompanyService companyService;

    public WorkingDay findById(Long id) {
        return workingDayRepository.findById(id).orElseThrow();
    }

    public WorkingDay save(WorkingDay workingDay) {
        return workingDayRepository.save(workingDay);
    }

    public List<WorkingDay> findAll() {
        return workingDayRepository.findAll();
    }

    public void deleteById(Long id) {
        workingDayRepository.deleteById(id);
    }
    public WorkingDay findByDate(LocalDate date){
        return workingDayRepository.findByDate(date);
    }

    public WorkingDay addWorkingDayToCompany(Long companyId, WorkingDay workingDay){
        companyService.findById(companyId).getWorkingDayList().add(workingDay);
        return save(workingDay);
    }

}
