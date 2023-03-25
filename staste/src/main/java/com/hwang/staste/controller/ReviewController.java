package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Review;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import com.hwang.staste.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @GetMapping("/review/{username}/{userId}")
    private List<Review> reviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        return reviews;
    }

    @GetMapping("/review/{storeId}")
    private List<Review> reviewsByStore(@RequestParam(value = "storeId", required = false) Long storeId) {
        List<Review> reviews = reviewService.getReviewsByStore(storeId);
        return reviews;
    }

    @GetMapping("/review/{storeId}/{foodId}")
    private List<Review> reviewsByFood(
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "foodId", required = false) Long foodId) {

        List<Review> reviews = reviewService.getReviewsByStoreByFood(storeId, foodId);
        return reviews;
    }

    @GetMapping("/reviews")
    public List<Review> getArticles() {
        return reviewService.getReviews();
    }

    @PostMapping("/review")
    private Review PostReview(@RequestBody Map<String, String> reviewForm) {
        User user = userRepository.findByUsername(reviewForm.get("username"));
        Review review = Review.builder()
                .user(user)
                .food(reviewForm.get("food"))
                .score(reviewForm.get("score"))

        return reviewService.postReview(review);
    }

    @DeleteMapping("/review/{username}/{reviewId}")
    private void DeleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
