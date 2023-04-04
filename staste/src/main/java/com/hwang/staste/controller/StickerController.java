package com.hwang.staste.controller;

import com.hwang.staste.model.entity.Sticker;
import com.hwang.staste.service.StickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StickerController {

    private final StickerService stickerService;

    @GetMapping("/stickers")
    private List<Sticker> getStickers(){
        return stickerService.getStickers();
    }

    @PostMapping("/sticker")
    private Sticker postSticker(@RequestBody Sticker sticker){
        return stickerService.postSticker(sticker);
    }
}
