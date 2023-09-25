package com.gainsight.casestudy.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Product product;

    @JoinColumn(name="userName")
    @ManyToOne
    private User user;

    public Cart(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
