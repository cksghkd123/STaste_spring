package com.hwang.staste.service;

import com.hwang.staste.model.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsByUser(Long userId);

    List<Review> getReviewsByStore(Long storeId);

    List<Review> getReviewsByStoreByFood(Long storeId,Long foodId);

    Review postReview(Review review);

    void deleteReview(Long reviewId);

}
