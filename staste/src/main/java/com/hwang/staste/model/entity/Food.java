package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
    @JoinColumn(name = "MARKET_ID")
    private Market market;

    public Food(String name, String explanation, Market market) {
        this.name = name;
        this.explanation = explanation;
        this.market = market;
    }
}
