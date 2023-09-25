package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.OrderDetails;
import com.gainsight.casestudy.Entity.OrderInput;
import com.gainsight.casestudy.Entity.OrderProductQuantity;
import com.gainsight.casestudy.Service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;
// placing an order
    @PostMapping("/placeOrder/{isCartCheckout}")
    public void placeOrder(@PathVariable(value="isCartCheckout") boolean isCartCheckout, @RequestBody OrderInput orderInput){
        orderDetailsService.placeOrder(isCartCheckout,orderInput);
    }

    //display all orders made by user
    @GetMapping("/showAllOrders")
    public ResponseEntity<List<OrderDetails>> showAllOrders(){
        List<OrderDetails> allOrders=orderDetailsService.showAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
    //display all orders made by user to admin and filter data by order status
    @GetMapping("/showAllOrdersToAdmin/{status}")
    public ResponseEntity<List<OrderDetails>> showAllOrdersToAdmin(@PathVariable String status){
        List<OrderDetails> allOrders=orderDetailsService.showAllOrdersToAdmin(status);
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
    //admin to make order status as delivered
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable int orderId){
        orderDetailsService.markOrderAsDelivered(orderId);
    }


}
