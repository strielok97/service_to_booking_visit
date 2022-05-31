package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.persistance.Company;
import com.example.service_to_booking_visit.persistance.Review;
import com.example.service_to_booking_visit.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ReviewServiceTest {
    @Mock
    ReviewRepository reviewRepository;
    @Mock
    CompanyService companyService;
    @InjectMocks
    ReviewService reviewService;



    List<Review> reviewList;
    Company company;


    @BeforeEach
    void setUp() {
        reviewList = List.of(
                new Review(1L, 4, "jest ok"),
                new Review(12L, 5, "idealnie, bez zastrzeżeń"),
                new Review(5L, 3, "długi czas oczekiwania")
        );
        company = new Company(1L, "ABBA", "Olsztyn", null, null, null, reviewList, null);
    }


    @Test
    void shouldReturnAverageCompanyRating() {
        //given
        Mockito.when(companyService.findById(any())).thenReturn(company);
        //when
        Double result = reviewService.getAverageCompanyRating(1L);
        //then
        assertEquals(4.0, result);

    }


}