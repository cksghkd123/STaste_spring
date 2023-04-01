package com.hwang.staste.dto;

import com.hwang.staste.model.entity.Food;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetMenuResponse {
    List<Food> menu;
    String marketName;

    public GetMenuResponse(List<Food> menu, String marketName) {
        this.menu = menu;
        this.marketName = marketName;
    }
}
