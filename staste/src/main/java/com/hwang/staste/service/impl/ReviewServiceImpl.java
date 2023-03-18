package com.hwang.staste.service.impl;

import com.hwang.staste.model.entity.Review;
import com.hwang.staste.repository.ReviewRepository;
import com.hwang.staste.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {
        return null;
    }

    @Override
    public List<Review> getReviewsByStore(Long storeId) {

        return null;
    }

    @Override
    public List<Review> getReviewsByStoreByFood(Long storeId, Long foodId) {
        return null;
    }

    @Override
    public Review postReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
