package com.hwang.staste.model.entity;

import javax.persistence.*;

@Entity
public class Food {

    @Id @Column(name = "FOOD_ID")
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

}
