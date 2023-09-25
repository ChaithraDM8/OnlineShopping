package com.gainsight.casestudy.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int orderId;
private String  orderUserName;
private String  orderUserAddress;
private String contactNumber;
private String orderStatus;
private double orderAmount;


@OneToOne
private Product product;

@OneToOne
private User user;


    public OrderDetails(String userName, String userAddress, String contactNumber, String orderStatus, double orderAmount, Product product, User user) {
        this.orderUserName = userName;
        this.orderUserAddress = userAddress;
        this.contactNumber = contactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
       // this.transactionDetails=transactionDetails;
    }
}
