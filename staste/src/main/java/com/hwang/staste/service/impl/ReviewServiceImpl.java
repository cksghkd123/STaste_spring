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

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NullPointerException("없는데용"));
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {

        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> getReviewsByStore(Long storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    @Override
    public List<Review> getReviewsByFood(Long foodId) {
        return reviewRepository.findByFoodId(foodId);
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
