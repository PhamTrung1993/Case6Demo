package com.example.module6demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(1000)")
    private String imageName;

//    public Image(String imageName) {
//        this.imageName = imageName;
//    }
}
