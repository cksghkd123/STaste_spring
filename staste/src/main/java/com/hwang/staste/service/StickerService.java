package com.hwang.staste.service;

import com.hwang.staste.model.entity.Sticker;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StickerService {
    List<Sticker> getStickers();

    Sticker postSticker(Sticker sticker);


}
