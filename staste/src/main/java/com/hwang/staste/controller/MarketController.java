package com.hwang.staste.controller;

import com.hwang.staste.dto.FoodDTO;
import com.hwang.staste.dto.GetMenuResponse;
import com.hwang.staste.dto.MarketDTO;
import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Market;
import com.hwang.staste.service.MarketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/markets")
    private List<MarketDTO> getMarkets() {
        List<Market> markets = marketService.getMarkets();
        return markets.stream()
                .map(market -> new MarketDTO(market.getId(), market.getName()))
                .collect(Collectors.toList());

    }

    @GetMapping("/market/{marketId}/menu")
    private GetMenuResponse getMarketFoods(@PathVariable Long marketId) {
        Market market = marketService.getMarket(marketId);
        List<FoodDTO> menu = market.getMenu().stream()
                .map(food -> new FoodDTO(food.getId(), food.getName(),food.getExplanation()))
                .collect(Collectors.toList());
        GetMenuResponse response = GetMenuResponse.builder()
                        .marketName(market.getName())
                        .menu(menu)
                        .build();


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
