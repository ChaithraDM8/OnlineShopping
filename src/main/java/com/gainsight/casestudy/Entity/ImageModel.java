package com.gainsight.casestudy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "images")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="image_id")
    private int id;
    private String name;
    private String type;
    @Column(length=5000000)
    private  byte[] image;

    @JoinColumn(name="id")
    @OneToOne
    Product product;

    public ImageModel(String originalFilename, String contentType, byte[] bytes) {
    this.name=originalFilename;
    this.type=contentType;
    this.image=bytes;
    }
}
