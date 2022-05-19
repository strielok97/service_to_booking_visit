package com.example.service_to_booking_visit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.repository.CompanyRepository;

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

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Page<Company> findAllByCity(String city, Pageable pageable) {
        return companyRepository.findAllByCity(city,pageable);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}
