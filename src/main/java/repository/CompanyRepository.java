package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistance.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
