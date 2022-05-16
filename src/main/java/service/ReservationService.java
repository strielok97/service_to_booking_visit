package service;

import lombok.RequiredArgsConstructor;
import persistance.Client;
import persistance.Reservation;

import javax.persistence.Entity;
import java.util.List;

@Entity
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(client);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

}
