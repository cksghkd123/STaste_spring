package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @Id
    @Column(name = "MARKET_ID")
    @GeneratedValue
    private Long id;

    private String name;


    @OneToMany(mappedBy = "market",cascade = CascadeType.ALL)
    private List<Food> menu;


    public Market(String name) {
        this.name = name;
    }
}
