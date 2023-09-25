package com.gainsight.casestudy.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentInput {

        private int orderId;
        private double amount;
        private String paymentMode;


}
