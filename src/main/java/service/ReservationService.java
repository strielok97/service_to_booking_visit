package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import persistance.Client;
import persistance.Reservation;
import repository.ReservationRepository;

import javax.persistence.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

}
