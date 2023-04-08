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
public class UserAbility {

    @Id
    @Column(name = "UA_ID")
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "userAbility")
    private User user;

    private Double hackLevel;

    private Double maraLevel;

    private Double tokLevel;

    private Double sweetLevel;

    private Double satisfyLevel;

    public UserAbility(Double hackLevel, Double maraLevel, Double tokLevel, Double sweetLevel, Double satisfyLevel) {
        this.hackLevel = hackLevel;
        this.maraLevel = maraLevel;
        this.tokLevel = tokLevel;
        this.sweetLevel = sweetLevel;
        this.satisfyLevel = satisfyLevel;
    }
}
