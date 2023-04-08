package com.hwang.staste.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id @Column(name = "USER_ID")
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    private String roles;


    @OneToOne(cascade = CascadeType.ALL)
    private UserAbility userAbility;

    @CreationTimestamp
    private Timestamp createDate;


    @Builder
    public User(String username, String password, String email, String roles,String provider, String providerId, Timestamp createDate, UserAbility userAbility){
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.createDate = createDate;
        this.userAbility = userAbility;
    }



}
