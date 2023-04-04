package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
