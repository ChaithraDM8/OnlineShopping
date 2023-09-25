package com.gainsight.casestudy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "MODE")
    private String paymentMode;

    @Column(name = "PAYMENT_DATE")
    private Instant paymentDate;

    @Column(name = "STATUS")
    private String paymentStatus;

    @Column(name = "AMOUNT")
    private double amount;

    public TransactionDetails(int orderId, String paymentMode, Instant paymentDate, String paymentStatus, double amount) {
        this.orderId = orderId;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }
}

