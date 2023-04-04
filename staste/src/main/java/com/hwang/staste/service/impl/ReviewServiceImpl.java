package com.hwang.staste.service.impl;

import com.hwang.staste.dto.PostReviewRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.Review;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.FoodRepository;
import com.hwang.staste.repository.ReviewRepository;
import com.hwang.staste.repository.UserRepository;
import com.hwang.staste.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private final FoodRepository foodRepository;

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NullPointerException("없는데용"));
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {

        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> getReviewsByMarket(Long marketId) {
        return reviewRepository.findByMarketId(marketId);
    }

    @Override
    public List<Review> getReviewsByFood(Long foodId) {
        return reviewRepository.findByFoodId(foodId);
    }

    @Override
    public Review postReview(PostReviewRequest reviewRequest) {
        User user = userRepository.findByUsername(reviewRequest.getUsername());
        Food food = foodRepository.findById(reviewRequest.getFoodId()).orElseThrow(() -> new NullPointerException("존재하지 않는 가게에용"));;
        Review review = Review.builder()
                .user(user)
                .food(food)
                .build();
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
