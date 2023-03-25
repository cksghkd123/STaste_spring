package com.hwang.staste.model.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
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

    private Long score;

    public Review(User user, Food food, Long score) {
        this.user = user;
        this.food = food;
        this.score = score;
    }
}
