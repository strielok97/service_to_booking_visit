package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistance.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
