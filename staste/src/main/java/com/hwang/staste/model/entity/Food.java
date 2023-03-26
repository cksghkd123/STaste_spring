package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @Column(name = "FOOD_ID")
    @GeneratedValue
    private Long id;

    private String name;

    private String explanation;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public Food(String name, String explanation, Store store) {
        this.name = name;
        this.explanation = explanation;
        this.store = store;
    }
}
