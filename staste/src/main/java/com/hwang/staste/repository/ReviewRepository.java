package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

    List<Review> findByMarketId(Long marketId);

    List<Review> findByFoodId(Long foodId);
}
