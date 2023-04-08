package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id @Column(name = "REVIEW_ID")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @ManyToMany
    @JoinTable(name = "review_sticker",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "sticker_id"))
    private List<Sticker> stickerList;



    public Review(User user, Food food, List<Sticker> stickerList) {
        this.user = user;
        this.food = food;
        this.stickerList = stickerList;
    }
}
