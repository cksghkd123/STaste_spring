package com.hwang.staste.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {

    @Id
    @Column(name = "STORE_ID")
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "store")
    private List<Food> menu;

}
