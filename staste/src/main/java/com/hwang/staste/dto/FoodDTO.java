package com.hwang.staste.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FoodDTO {
    private Long id;
    private String name;
    private String explanation;
}