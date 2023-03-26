package com.hwang.staste.service;

import com.hwang.staste.model.entity.Review;

import java.util.List;

public interface ReviewService {

    Review getReview(Long reviewId);
    List<Review> getReviewsByUser(Long userId);

    List<Review> getReviewsByStore(Long storeId);

    List<Review> getReviewsByFood(Long foodId);

    Review postReview(Review review);

    void deleteReview(Long reviewId);

}
