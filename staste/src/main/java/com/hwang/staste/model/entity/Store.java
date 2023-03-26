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
public class Store {

    @Id
    @Column(name = "STORE_ID")
    @GeneratedValue
    private Long id;

    private String name;


    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Food> menu;


    public Store(String name) {
        this.name = name;
    }
}
