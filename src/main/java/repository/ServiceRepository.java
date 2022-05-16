package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistance.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
