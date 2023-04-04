package com.hwang.staste.service.impl;

import com.hwang.staste.model.entity.Sticker;
import com.hwang.staste.repository.StickerRepository;
import com.hwang.staste.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StickerServiceImpl implements StickerService {

    private final StickerRepository stickerRepository;

    public StickerServiceImpl(StickerRepository stickerRepository) {
        this.stickerRepository = stickerRepository;
    }


    @Override
    public List<Sticker> getStickers() {
        return stickerRepository.findAll();
    }

    @Override
    public Sticker postSticker(Sticker sticker) {
        return stickerRepository.save(sticker);
    }
}
