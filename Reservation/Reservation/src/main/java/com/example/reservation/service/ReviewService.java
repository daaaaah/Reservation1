package com.example.reservation.service;

import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.model.Review;
import com.example.reservation.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //사용자가 리뷰를 작성할 수 있습니다.
    public Review writeReview(ReviewDTO reviewDTO) {

        Review review = new Review();
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());

        return reviewRepository.save(review);
    }

    //ID를 기준으로 리뷰의 정보를 검색합니다.
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}