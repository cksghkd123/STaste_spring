package com.hwang.staste.model.entity;

import javax.persistence.*;

@Entity
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
}
