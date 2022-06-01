package com.example.service_to_booking_visit.web;

import com.example.service_to_booking_visit.persistance.Review;
import com.example.service_to_booking_visit.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")

public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Review> getId(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/averageRatings/{companyId}")
    ResponseEntity<Double> getAverageCompanyRating(@PathVariable  Long companyId) {
        return ResponseEntity.ok(reviewService.getAverageCompanyRating(companyId));
    }

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.save(review));
    }

    @PostMapping("/rates/{companyId}")
    public ResponseEntity<Review> rateCompany(@RequestBody Review review, @PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.rateCompany(review,companyId));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        reviewService.deleteById(id);
    }
}
