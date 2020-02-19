package com.praveen.springsecurityjpa.entity;

import com.praveen.springsecurityjpa.enums.Gender;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false,updatable = false)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "gender",columnDefinition = "enum ('M','F')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public Gender getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }
}
