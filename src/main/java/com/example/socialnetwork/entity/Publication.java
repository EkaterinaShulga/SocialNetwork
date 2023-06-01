package com.example.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @OneToOne(mappedBy = "publication", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Photo photo;

}
