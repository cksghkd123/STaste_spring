package com.hwang.staste.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
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
    private String provider;
    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public User(String username, String password, String email, String provider, String providerId, Timestamp createDate){
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }

}
