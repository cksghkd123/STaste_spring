package com.hwang.staste.service.impl;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.FoodAbility;
import com.hwang.staste.model.entity.Market;
import com.hwang.staste.repository.FoodRepository;
import com.hwang.staste.repository.MarketRepository;
import com.hwang.staste.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {
    private final MarketRepository marketRepository;
    private final FoodRepository foodRepository;

    public MarketServiceImpl(MarketRepository marketRepository, FoodRepository foodRepository) {
        this.marketRepository = marketRepository;
        this.foodRepository = foodRepository;
    }


    @Override
    public Market getMarket(Long id) {
        return marketRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 가게에용"));
    }

    @Override
    public Market postNewMarket(Market market) {
        return marketRepository.save(market);
    }

    @Override
    public List<Market> getMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public Food postFood(PostFoodRequest foodRequest) {
        Long marketId = foodRequest.getMarketId();
        if (marketId == null) {
            throw new IllegalArgumentException("잘못된 경로인데요...");
        }

        Market market = getMarket(marketId);
        if (market == null) {
            throw new IllegalArgumentException("그런 가게는 없는데요... " + marketId);
        }

        Double score = Double.valueOf(5);
        FoodAbility foodAbility = FoodAbility.builder()
                .hackLevel(score)
                .maraLevel(score)
                .tokLevel(score)
                .sweetLevel(score)
                .satisfyLevel(score)
                .build();

        Food food = Food.builder()
                .name(foodRequest.getName())
                .explanation(foodRequest.getExplanation())
                .market(market)
                .foodAbility(foodAbility)
                .build();

        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long foodId) {
        foodRepository.deleteById(foodId);
    }
}
