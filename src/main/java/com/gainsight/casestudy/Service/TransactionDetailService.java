package com.gainsight.casestudy.Service;

import com.gainsight.casestudy.Entity.OrderDetails;
import com.gainsight.casestudy.Entity.PaymentInput;
import com.gainsight.casestudy.Entity.TransactionDetails;
import com.gainsight.casestudy.Repository.OrderDetailsRepository;
import com.gainsight.casestudy.Repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionDetailService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;

    private static final String TRANS_STATUS="Cash will be paid once the order is delivered";

    public int doPayment(PaymentInput paymentInput) {
        OrderDetails orderDetail = orderDetailsRepository.findById(paymentInput.getOrderId()).get();
        TransactionDetails transactionDetails=null;
        if (transactionDetailsRepository.findByOrderId(paymentInput.getOrderId())==null) {
            if (orderDetail.getOrderAmount() != paymentInput.getAmount())
                throw new RuntimeException("Amount should match");
                   transactionDetails = new TransactionDetails(
                    paymentInput.getOrderId(),
                    paymentInput.getPaymentMode(),
                    Instant.now(),
                    TRANS_STATUS, orderDetail.getOrderAmount()
            );
           transactionDetails= transactionDetailsRepository.save(transactionDetails);
        }
        return transactionDetails.getId();
    }

    public TransactionDetails getPaymentDetailsByOrderId(int orderId) {
        return transactionDetailsRepository.findByOrderId(orderId);
        }

    public TransactionDetails findById(int transId) {
        return transactionDetailsRepository.findById(transId).get();
    }
}

