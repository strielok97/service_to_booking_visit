package service;

import lombok.RequiredArgsConstructor;
import persistance.Client;
import persistance.Service;
import repository.ServiceRepository;

import javax.persistence.Entity;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

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

}
