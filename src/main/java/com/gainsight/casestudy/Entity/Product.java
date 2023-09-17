package com.gainsight.casestudy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="product_id")
    private int id;
    private String productName;
    private String productDescription;
    private int price;
    private String image;
    //private MultipartFile[] file;
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(name ="product_images",
//    joinColumns = {
//            @JoinColumn(name="product_id")
//    },
//    inverseJoinColumns = {
//            @JoinColumn(name="image_id")
//    })
   // private Set<ImageModel> productImages;
//    @OneToOne(mappedBy = "product")
//    private ImageModel productImages;

}
