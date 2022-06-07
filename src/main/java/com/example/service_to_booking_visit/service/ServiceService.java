package com.example.service_to_booking_visit.service;

import lombok.RequiredArgsConstructor;
import com.example.service_to_booking_visit.persistance.Service;
import com.example.service_to_booking_visit.repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CompanyService companyService;

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

    public Page<Service> getServicesByPriceAsc(Pageable pageable) {
        return serviceRepository.sortServicesByPriceAsc(pageable);
    }

    public Page<Service> getServicesByPriceDesc(Pageable pageable) {
        return serviceRepository.sortServicesByPriceDesc(pageable);
    }

    public Service addServiceToCompany(Long companyId, Service service){
        companyService.findById(companyId).getServiceList().add(service);
        return save(service);
    }


}
