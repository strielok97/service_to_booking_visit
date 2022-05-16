package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistance.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
