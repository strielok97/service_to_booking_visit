package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistance.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
