package com.example.service_to_booking_visit;

import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.persistance.Service;
import com.example.service_to_booking_visit.repository.CompanyRepository;
import com.example.service_to_booking_visit.repository.ServiceRepository;
import com.example.service_to_booking_visit.service.CompanyService;
import com.example.service_to_booking_visit.service.ServiceService;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;
    @InjectMocks
    private ServiceService serviceService;

    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    List<Service> firstServiceList;

    @BeforeEach
    void setUp(){
        List<Service> firstServiceList = List.of(
                new Service(2L,"Strzyzenie", 100.0 ),
                new Service(21L,"Malowanie paznokci", 150.0 )
        );

//        List<Company> companyList = List.of(
//                new Company(1L,"ABBA","Olsztyn", null, null, firstServiceList),
//                new Company(19L,"Queen","Sopot", null, null, firstServiceList)
//        );

        Mockito.when(companyRepository.findAllByCity("Olsztyn",PageRequest.of(0,25)))
                .thenReturn(new PageImpl<Company>(Collections.EMPTY_LIST));
        Mockito.when(serviceRepository.findServicesByCity("Olsztyn")).thenReturn(Collections.EMPTY_LIST);
    }

    @Test
    void shouldReturnServicesInGivenCity() {
        //when
        List<Service> result = serviceService.getAvailableServicesByLocalization("Olsztyn");
        //then
        assertEquals(0, result.size());

    }




}