package com.hwang.staste.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserAbility {

    @Id
    @Column(name = "UA_ID")
    @GeneratedValue
    private Long id;

    private Double hackLevel;

    private Double maraLevel;

    private Double tokLevel;

    private Double sweetLevel;

    private Double satisfyLevel;

}
