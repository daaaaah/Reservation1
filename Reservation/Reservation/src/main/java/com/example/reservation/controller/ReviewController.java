package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.model.Review;
import com.example.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return convertToDTO(review);
    }

    private ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getContent(),
                review.getRating()
        );
    }

    @PostMapping
    public ResponseEntity<Review> writeReview(@RequestBody ReviewDTO reviewDTO) {
        Review savedReview = reviewService.writeReview(reviewDTO);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
}