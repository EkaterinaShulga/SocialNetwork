package com.example.socialnetwork.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileSize;
    private String mediaType;
    private byte[] photo;

   /* @ManyToOne()
    @JoinColumn(name = "id_publication")
    Publication publication;*/
}
