package com.example.service_to_booking_visit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}
