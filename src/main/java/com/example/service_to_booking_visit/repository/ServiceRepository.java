package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select service.name, service.payment , company.city, company.company_name from company\n" +
            "join company_service_list on company.id = company_service_list.company_id\n" +
            "join service on company_service_list.service_list_id = service.id \n" +
            "order by payment", nativeQuery = true)
    Page<Service> sortServicesByPriceAsc(Pageable pageable);

    @Query(value = "select service.name, service.payment , company.city, company.company_name from company\n" +
            "join company_service_list on company.id = company_service_list.company_id\n" +
            "join service on company_service_list.service_list_id = service.id \n" +
            "order by payment desc", nativeQuery = true)
    Page<Service> sortServicesByPriceDesc(Pageable pageable);




}
