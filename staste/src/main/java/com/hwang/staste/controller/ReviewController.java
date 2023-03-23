package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Review;
import com.hwang.staste.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/user/{userId}")
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
    private Review PostReview(@RequestBody Review review) {
        return reviewService.postReview(review);
    }

    @DeleteMapping("/review/{id}")
    private void DeleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
