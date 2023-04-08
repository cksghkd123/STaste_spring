package com.hwang.staste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FoodAbility {

    @Id
    @Column(name = "FA_ID")
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "foodAbility")
    private Food food;

    private Double hackLevel;

    private Double maraLevel;

    private Double tokLevel;

    private Double sweetLevel;

    private Double satisfyLevel;

    public FoodAbility (Double hackLevel, Double maraLevel, Double tokLevel, Double sweetLevel, Double satisfyLevel) {
        this.hackLevel = hackLevel;
        this.maraLevel = maraLevel;
        this.tokLevel = tokLevel;
        this.sweetLevel = sweetLevel;
        this.satisfyLevel = satisfyLevel;
    }
}
