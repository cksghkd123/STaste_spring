package com.hwang.staste.controller;

import com.hwang.staste.dto.GetMenuResponse;
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

    @GetMapping("/markets")
    private List<Market> getMarkets() {
        return marketService.getMarkets();
    }

    @GetMapping("/market/{marketId}/menu")
    private GetMenuResponse getMarketFoods(@PathVariable Long marketId) {
        Market market = marketService.getMarket(marketId);
        GetMenuResponse response = GetMenuResponse.builder()
                        .marketName(market.getName())
                        .menu(market.getMenu())
                        .build();

        System.out.println("Menu:");
        for (Food food : response.getMenu()) {
            System.out.println(food.toString());
        }
        return response;
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
