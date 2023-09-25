package com.gainsight.casestudy.Service;

import com.gainsight.casestudy.Entity.Cart;
import com.gainsight.casestudy.Entity.Product;
import com.gainsight.casestudy.Entity.User;
import com.gainsight.casestudy.Repository.CartRepository;
import com.gainsight.casestudy.Repository.ProductRepository;
import com.gainsight.casestudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gainsight.casestudy.Controller.UserController.USERNAME;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepo;
    public Cart addToCart(int productId, String name){
        System.out.println("Add to cart service.");
       Product product= productRepository.findById(productId).get();
        System.out.println(product.getProductName());
        Cart cart=null;
        //getting the current user who logged in
        User user=userRepo.findById(name).get();


//        //removing the duplicate products
        List<Cart> cartList=cartRepository.findByUser(user);
       List<Cart> filteredList=cartList.stream().filter(x->x.getProduct().getId()==productId).collect(Collectors.toList());
        if(filteredList.size()>0) return null;//throw new RuntimeException("product is already added!");

        //adding product to cart
       if(product!=null && user!=null) {
           cart = new Cart(product, user);
           System.out.println("here here here.1");
           cartRepository.save(cart);
       }
        System.out.println("here here here.12");
           return cart;


    }
    public List<Cart> getCartDetails(){
        User user1=userRepo.findById(USERNAME).get();
    User user=userRepo.findById(user1.getUserName()).get();
        System.out.println(user.getUserName());
    return cartRepository.findByUser(user);
    }

    public boolean deleteCartItem(int cartId) {
        long count=cartRepository.count();

                if(cartRepository.findById(cartId).get()==null) throw new RuntimeException ("item not exist");
                else
                   cartRepository.deleteById(cartId);
        return count>cartRepository.count();

    }
}
