package com.gainsight.casestudy.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {
    private String userName;
    private String userAddress;
    private String contactNumber;
    private String transactionMode;
    private List<OrderProductQuantity> orderProductQuantityList;


}
