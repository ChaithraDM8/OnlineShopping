package com.gainsight.casestudy.Entity;


import jakarta.persistence.*;
import lombok.*;


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
    private long quantity;

}
