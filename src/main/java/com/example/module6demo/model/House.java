package com.example.module6demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(columnDefinition = "nvarchar(200)")
    @NotEmpty
    private String houseName;
    @Column(columnDefinition = "nvarchar(800)")
    @NotEmpty
    private String houseAddress;
    private int bedrooms;
    private int bathrooms;
    @Column(columnDefinition = "nvarchar(1000)")
    private String description;

    private long rent;

    @OneToMany
    @JoinTable(name = "houses_image", joinColumns = {@JoinColumn(name = "house_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_status_id")
    private HouseStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
