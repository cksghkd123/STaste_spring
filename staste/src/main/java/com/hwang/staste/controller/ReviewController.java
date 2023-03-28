package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Review;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import com.hwang.staste.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @GetMapping("/review/user/{username}")
    private List<Review> getReviewsByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        List<Review> reviews = reviewService.getReviewsByUser(user.getId());
        return reviews;
    }

    @GetMapping("/review/market/{marketId}")
    private List<Review> reviewsByMarket(@RequestParam(value = "marketId", required = false) Long marketId) {
        List<Review> reviews = reviewService.getReviewsByMarket(marketId);
        return reviews;
    }

    @GetMapping("/review/food/{foodId}")
    private List<Review> reviewsByFood(
            @RequestParam(value = "foodId", required = false) Long foodId){
        List<Review> reviews = reviewService.getReviewsByFood(foodId);
        return reviews;
    }

    @PostMapping("/review")
    private Review PostReview(@RequestBody Review review) {
        return reviewService.postReview(review);
    }

    @DeleteMapping("/review/{reviewId}")
    private void DeleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
