package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long>/*, PagingAndSortingRepository<Service, Long>*/ {
//    List<Service> findAll(double price, Pageable pageable);


    @Query(value = "select service.* from company\n" +
            "join company_service_list on company.id = company_service_list.company_id\n" +
            "join service on company_service_list.service_list_id = service.id \n" +
            "where company.city = ?1 ", nativeQuery = true)
    List<Service> findServicesByCity(String city);

}
