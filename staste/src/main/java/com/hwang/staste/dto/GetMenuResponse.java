package com.hwang.staste.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetMenuResponse {
    private List<FoodDTO> menu;
    private String marketName;

    public GetMenuResponse(List<FoodDTO> menu, String marketName) {
        this.menu = menu;
        this.marketName = marketName;
    }
}
