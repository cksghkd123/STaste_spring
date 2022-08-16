package com.hwang.staste.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
public class Sticker {
    @Id
    @Column(name = "sticker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long date;// 포스트 된 시간

    @Column
    private Long stickerNumber;

    @Builder
    public Sticker(Long stickerNumber) {
        this.stickerNumber = stickerNumber;
    }


}
