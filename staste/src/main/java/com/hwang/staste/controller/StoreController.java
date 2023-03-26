package com.hwang.staste.controller;

import com.hwang.staste.dto.PostFoodRequest;
import com.hwang.staste.model.entity.Store;
import com.hwang.staste.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    private List<Store> getStores() {
        return storeService.getStores();
    }

    @GetMapping("/store/{storeId}")
    private Store getStore(@PathVariable Long storeId) {
        return storeService.getStore(storeId);
    }

    @PostMapping("/food")
    private String postFood(@RequestBody PostFoodRequest foodRequest) {
        storeService.postFood(foodRequest);

        return "음식을 등록했습니다만..";
    }

    @DeleteMapping("/food/{foodId}")
    private void deleteFood(@PathVariable Long foodId) {
        storeService.deleteFood(foodId);
    }

}
