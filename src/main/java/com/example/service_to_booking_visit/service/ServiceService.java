package com.example.service_to_booking_visit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import com.example.service_to_booking_visit.persistance.Service;
import com.example.service_to_booking_visit.repository.ServiceRepository;
import com.example.service_to_booking_visit.web.CompanyController;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CompanyService companyService;
    private final CompanyController companyController;

    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    public List<Service> getAvailableServicesByLocalization(String city) {
       return serviceRepository.findServicesByCity(city);
    }

}
