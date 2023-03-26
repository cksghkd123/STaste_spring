package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
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
    @JoinColumn(name = "STORE_ID")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    private Long score;

    public Review(User user, Food food, Store store, Long score) {
        this.user = user;
        this.food = food;
        this.store = store;
        this.score = score;
    }
}
