package com.example.service_to_booking_visit.repository;

import com.example.service_to_booking_visit.persistance.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value =
            "select avg(review.rating) as 'average' " +
            "from company " +
            "join company_review_list on company_id= company_review_list.company_id " +
            "join review on company_review_list.review_list_id = review.id " +
            "where company_id=?1 " +
            "group by company_id", nativeQuery = true)
    Double getAverageCompanyRating(Long companyId);
}
