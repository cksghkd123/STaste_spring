package com.hwang.staste.dto;

import com.hwang.staste.model.entity.Sticker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private List<Sticker> stickerList;
    private String foodName;
}
