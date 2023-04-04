package com.hwang.staste.repository;

import com.hwang.staste.model.entity.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StickerRepository extends JpaRepository<Sticker, Long> {
}
