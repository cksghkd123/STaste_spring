package com.hwang.staste.service.impl;

import com.hwang.staste.algorithm.ReviewAlgo;
import com.hwang.staste.dto.PostReviewRequest;
import com.hwang.staste.model.entity.*;
import com.hwang.staste.repository.*;
import com.hwang.staste.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final ReviewAlgo reviewAlgo;
    private final UserAbilityRepository userAbilityRepository;
    private final FoodAbilityRepository foodAbilityRepository;

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NullPointerException("없는데용"));
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {

        return reviewRepository.findByUserId(userId);
    }


    @Override
    public List<Review> getReviewsByFood(Long foodId) {
        return reviewRepository.findByFoodId(foodId);
    }

    @Override
    public String postReview(PostReviewRequest reviewRequest) {
        User user = userRepository.findByUsername(reviewRequest.getUsername());
        Food food = foodRepository.findById(reviewRequest.getFoodId()).orElseThrow(() -> new NullPointerException("존재하지 않는 가게에용"));

        UserAbility userAbility = user.getUserAbility();
        FoodAbility foodAbility = food.getFoodAbility();
        reviewAlgo.updateAbility(userAbility, foodAbility, reviewRequest.getStickerList());


        userAbilityRepository.save(userAbility);
        foodAbilityRepository.save(foodAbility);

        Review review = Review.builder()
                .user(user)
                .food(food)
                .stickerList(reviewRequest.getStickerList())
                .build();
        reviewRepository.save(review);

        return "리뷰 등록완료";
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
