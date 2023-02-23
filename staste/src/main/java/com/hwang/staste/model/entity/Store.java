package com.hwang.staste.model.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Store {

    @Id @Column(name = "STORE_ID")
    @GeneratedValue
    private Long id;



}
