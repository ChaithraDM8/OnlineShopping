package com.gainsight.casestudy.Service;

import com.gainsight.casestudy.Entity.*;
import com.gainsight.casestudy.Repository.CartRepository;
import com.gainsight.casestudy.Repository.OrderDetailsRepository;
import com.gainsight.casestudy.Repository.ProductRepository;
import com.gainsight.casestudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.gainsight.casestudy.Controller.UserController.USERNAME;

@Service
public class OrderDetailsService {
    private static final String ORDER_PLACED="Placed";
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepo;

    @Autowired
    TransactionDetailService transactionDetailService;



    public void placeOrder(boolean isCartCheckout,OrderInput orderInput) {
        if(orderInput.getOrderProductQuantityList().size()<1) throw new RuntimeException("Add products");

        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        User user1=userRepo.findById(USERNAME).get();
        User user= userRepo.findById(user1.getUserName()).get();//get currentuser details once security class is implemented
         List<OrderDetails> orderDetailsList=new ArrayList<>();

        for (OrderProductQuantity q : productQuantityList) {
            if(q.getQuantity()<1) throw new RuntimeException("quantity should not be lessthan 1");
            Product product = productRepository.findById(q.getProductId()).get();
            if(q.getQuantity()>product.getQuantity()) throw new RuntimeException("donot add the quantity morethan the available quantity");
            OrderDetails orderDetails = new OrderDetails(
                    orderInput.getUserName(),
                    orderInput.getUserAddress(),
                    orderInput.getContactNumber(),
                    ORDER_PLACED,
                    product.getPrice() * q.getQuantity(),
                   product,user
            );
            orderDetailsRepository.save(orderDetails);
            orderDetailsList.add(orderDetails);
            PaymentInput paymentInput=new PaymentInput(orderDetails.getOrderId(),orderDetails.getOrderAmount(),orderInput.getTransactionMode());
            transactionDetailService.doPayment(paymentInput);
        }

        //empty the cart once order is placed
       // if (isCartCheckout) {
            List<Cart> cart = cartRepository.findByUser(user);
            cart.stream().forEach(x -> cartRepository.delete(x));
       // }

    }

    public List<OrderDetails> showAllOrders() {
        User user1=userRepo.findById(USERNAME).get();
        User username= userRepo.findById(user1.getUserName()).get();
        List<OrderDetails> listOfOrders=orderDetailsRepository.findByUser(username);
        return listOfOrders;
    }

    public List<OrderDetails> showAllOrdersToAdmin(String status) {
        List<OrderDetails> listOfOrders=new ArrayList<>();
        if(status.equalsIgnoreCase("all"))
          listOfOrders = orderDetailsRepository.findAll();

        else
            listOfOrders=orderDetailsRepository.findByOrderStatus(status);

        return listOfOrders;
    }


    public void markOrderAsDelivered(int orderId) {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderId).get();
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Delivered");
            orderDetailsRepository.save(orderDetails);
        }
    }
}
