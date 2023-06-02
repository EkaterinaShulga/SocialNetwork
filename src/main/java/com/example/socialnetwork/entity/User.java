package com.example.socialnetwork.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String regDate;
    private String password;
    private Role role;
    private String username;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Publication> allPublication;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Subscription> allSubscriptions;


}
