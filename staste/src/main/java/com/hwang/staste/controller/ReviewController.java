package com.hwang.staste.controller;

import com.hwang.staste.dto.FoodDTO;
import com.hwang.staste.dto.PostReviewRequest;
import com.hwang.staste.dto.ReviewDTO;
import com.hwang.staste.model.entity.Market;
import com.hwang.staste.model.entity.Review;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import com.hwang.staste.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @GetMapping("/review/user/{username}")
    private List<ReviewDTO> getReviewsByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        List<Review> reviews = reviewService.getReviewsByUser(user.getId());
        List<ReviewDTO> response = new ArrayList<>();
        for (Review review : reviews) {
            response.add(new ReviewDTO(review.getStickerList(), review.getFood().getName()));
        }
        return response;
    }

    @GetMapping("/review/food/{foodId}")
    private List<Review> reviewsByFood(
            @RequestParam(value = "foodId", required = false) Long foodId){
        List<Review> reviews = reviewService.getReviewsByFood(foodId);
        return reviews;
    }

    @PostMapping("/review")
    private String PostReview(@RequestBody PostReviewRequest reviewRequest) {
        return reviewService.postReview(reviewRequest);
    }

    @DeleteMapping("/review/{reviewId}")
    private void DeleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
