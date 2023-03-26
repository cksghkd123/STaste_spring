package com.hwang.staste.service.impl;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.Store;
import com.hwang.staste.repository.FoodRepository;
import com.hwang.staste.repository.StoreRepository;
import com.hwang.staste.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;

    public StoreServiceImpl(StoreRepository storeRepository, FoodRepository foodRepository) {
        this.storeRepository = storeRepository;
        this.foodRepository = foodRepository;
    }


    @Override
    public Store getStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new NullPointerException("존재하지 않는 가게에용"));
    }

    @Override
    public Store postNewStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    @Override
    public Food postFood(PostFoodRequest foodRequest) {
        Long storeId = foodRequest.getStoreId();
        if (storeId == null) {
            throw new IllegalArgumentException("The given store id must not be null!");
        }

        Store store = getStore(storeId);
        if (store == null) {
            throw new IllegalArgumentException("No store found with id: " + storeId);
        }

        Food food = Food.builder()
                .name(foodRequest.getName())
                .explanation(foodRequest.getExplanation())
                .store(store)
                .build();

        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long foodId) {
        foodRepository.deleteById(foodId);
    }
}
