package com.example.service_to_booking_visit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.service_to_booking_visit.persistance.Client;
import com.example.service_to_booking_visit.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
