package com.gainsight.casestudy.Service;


import com.gainsight.casestudy.Entity.Product;
import com.gainsight.casestudy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;
//    @Autowired
//    RestTemplate restTemplate;

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
    public boolean addOrModifyProduct( Product product) throws IOException {
       // MultipartFile file=product.getFile();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains(".."))
//            System.out.println("not a valid file");
//
//        product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

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
    public Product getProductByName(String productName){
        Optional<Product> optionalProduct=productRepo.findByProductName(productName);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        throw new RuntimeException("Product doesnot exist");
    }
}
