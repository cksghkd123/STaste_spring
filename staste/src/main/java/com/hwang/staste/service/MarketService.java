package com.hwang.staste.service;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.Market;

import java.util.List;

public interface MarketService {

    Market getMarket(Long id);

    List<Market> getMarkets();

    Market postNewMarket(Market market);

    Food postFood(PostFoodRequest foodRequest);
    void deleteFood(Long foodId);

}
