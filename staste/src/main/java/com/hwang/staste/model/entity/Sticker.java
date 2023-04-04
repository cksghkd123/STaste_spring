package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sticker {
    @Id
    @Column(name = "STICKER_ID")
    @GeneratedValue
    private Long id;

    private Long hackLevel;

    private Long maraLevel;

    private Long tokLevel;

    private Long sweetLevel;

    private Long satisfyLevel;

}
