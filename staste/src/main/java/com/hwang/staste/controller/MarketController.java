package com.hwang.staste.controller;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.Market;
import com.hwang.staste.service.MarketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/stores")
    private List<Market> getMarkets() {
        return marketService.getMarkets();
    }

    @GetMapping("/market/{marketId}/menu")
    private List<Food> getMarketFoods(@PathVariable Long marketId) {
        Market market = marketService.getMarket(marketId);
        return market.getMenu();
    }

    @PostMapping("/food")
    private String postFood(@RequestBody PostFoodRequest foodRequest) {
        marketService.postFood(foodRequest);

        return "음식을 등록했습니다만..";
    }

    @DeleteMapping("/food/{foodId}")
    private void deleteFood(@PathVariable Long foodId) {
        marketService.deleteFood(foodId);
    }

}
