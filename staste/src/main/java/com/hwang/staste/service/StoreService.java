package com.hwang.staste.service;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Food;
import com.hwang.staste.model.entity.Store;

import java.util.List;

public interface StoreService {

    Store getStore(Long id);

    List<Store> getStores();

    Store postNewStore(Store store);

    Food postFood(PostFoodRequest foodRequest);
    void deleteFood(Long foodId);

}
