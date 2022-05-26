package com.example.service_to_booking_visit.service;

import com.example.service_to_booking_visit.enumerated.Rating;
import com.example.service_to_booking_visit.persistance.Review;
import com.example.service_to_booking_visit.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review rateCompany(Review review, Long companyId) {
        companyService.findById(companyId).getReviewList().add(review);
        return save(review);
    }

    public Double getAverageCompanyRating(Long companyId) {
        int size = companyService.findById(companyId).getReviewList().size();
        int sumRating = companyService.findById(companyId).getReviewList().stream()
                .map(Review::getRating)
                .map(Rating::getScore)
                .mapToInt(Integer::intValue)
                .sum();

        return (double) sumRating / size;
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
