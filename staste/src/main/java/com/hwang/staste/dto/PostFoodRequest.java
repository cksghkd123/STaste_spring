package com.hwang.staste.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostFoodRequest {
    private String name;
    private String explanation;
    private Long storeId;
}
