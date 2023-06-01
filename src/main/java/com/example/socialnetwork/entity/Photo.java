package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fileSize;
    private String mediaType;
    private byte[] photo;

    @OneToOne
    @JoinColumn(name = "id_publication")
    private Publication publication;
}
