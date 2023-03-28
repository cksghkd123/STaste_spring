package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market,Long> {
}
