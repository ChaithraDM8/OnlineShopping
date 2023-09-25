package com.gainsight.casestudy.Service;


import com.gainsight.casestudy.Controller.UserController;
import com.gainsight.casestudy.Entity.Cart;
import com.gainsight.casestudy.Entity.Product;
import com.gainsight.casestudy.Entity.User;
import com.gainsight.casestudy.Exceptions.ProductException;
import com.gainsight.casestudy.Repository.CartRepository;
import com.gainsight.casestudy.Repository.ProductRepository;
import com.gainsight.casestudy.Repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gainsight.casestudy.Controller.UserController.USERNAME;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;
//    @Autowired
//    RestTemplate restTemplate;
@Autowired
UserRepository userRepo;

@Autowired
    CartRepository cartRepository;


    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(int pId){
        Optional<Product> op= productRepo.findById(pId);
        if(op.isPresent()) return op.get();
        throw new RuntimeException("product not found");
    }

    @Transactional
    public boolean addOrModifyProduct( Product product) throws ProductException {
        if(product.getProductName() ==null || product.getProductDescription()==null)
            throw new ProductException("Field value should not be Empty!");
        if(product.getQuantity()<1) throw new RuntimeException("Quantity should not be <1");
        if(product.getPrice()<1) throw new RuntimeException("price should not be <1");
        Product p=productRepo.save(product);
        return p!=null;
    }

    @Transactional
    public boolean deleteProductById(int productId){
        long count=productRepo.count();
        productRepo.deleteById(productId);
        return count>productRepo.count();
    }

    @Transactional
    public List<Product> getProductByName(String productName){
        List<Product> optionalProduct=productRepo.findByProductName(productName);
        if(optionalProduct.size()>0)
            return optionalProduct;
        throw new RuntimeException("Product doesnot exist");
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, int productId) {
        List<Product> list =new ArrayList<>();
        //if only single product present means user clicks on buynow button
        if(isSingleProductCheckout && productId!=0){
            Product product=productRepo.findById(productId).get();
            list.add(product);
            return list;
            }

        else
    {
        //multiple items present in cart,checkout the entire cart
        //get current user

        User user=userRepo.findById(USERNAME).get();
        List<Cart> carts =cartRepository.findByUser(user);

        return carts.stream().map(x->x.getProduct()).collect(Collectors.toList());
    }

    }
}
