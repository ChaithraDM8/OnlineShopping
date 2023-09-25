package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.PaymentInput;
import com.gainsight.casestudy.Entity.TransactionDetails;
import com.gainsight.casestudy.Service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransactionDetailsController {

    @Autowired
    TransactionDetailService transactionDetailService;

    @PostMapping("/doPayment")
    public ResponseEntity<Integer> doPayment(@RequestBody PaymentInput paymentInput) {
        return new ResponseEntity<>(transactionDetailService.doPayment(paymentInput), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<TransactionDetails> getPaymentDetailsByOrderId(@PathVariable int orderId) {
        return new ResponseEntity<>(transactionDetailService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK);
    }
}
