package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.Cart;
import com.gainsight.casestudy.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable(value="productId") int productId,Authentication authentication){
       // System.out.println("this is add to cart"+authentication.getName());
        Cart cart= cartService.addToCart(productId,authentication.getName());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @GetMapping("/getCartDetails")
    public ResponseEntity<List<Cart>> getCartDetails(){
        return new ResponseEntity<>(cartService.getCartDetails(),HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public HttpStatus deleteCartItem(@PathVariable(value="cartId") int cartId){
        if(cartService.deleteCartItem(cartId))
            return HttpStatus.OK;
        else
            return HttpStatus.CREATED;
    }
}
