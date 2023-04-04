package com.hwang.staste.dto;

import com.hwang.staste.model.entity.Sticker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostReviewRequest {

    private String username;
    private Long foodId;

    private List<Sticker> stickerList;
}
