package com.hwang.staste.service;

import com.hwang.staste.model.entity.Sticker;

import java.util.List;

public interface StickerService {
    List<Sticker> getAllStickers();

    List<Sticker> getStickers();

    List<Sticker> postStickers(List<Sticker> stickers);

    void deleteSticker(Long stickerId);
}
