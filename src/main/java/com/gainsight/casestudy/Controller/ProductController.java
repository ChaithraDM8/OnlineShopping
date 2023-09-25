package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.Product;
import com.gainsight.casestudy.Exceptions.ProductException;
import com.gainsight.casestudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") int productId) {
        Product p = productService.getProductById(productId);
        if (p != null)
            return new ResponseEntity<>(p, HttpStatus.OK);
        return new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value={"/addProduct"})
    public HttpStatus addProduct(@RequestBody Product product) throws ProductException {
            if (productService.addOrModifyProduct(product))
               return HttpStatus.OK;
        return HttpStatus.CREATED;
        }


    @PutMapping("/modifyProduct")
    public HttpStatus modifyProduct(MultipartFile file,@RequestBody Product product) throws ProductException {
        if (productService.addOrModifyProduct(product))
            return HttpStatus.OK;
        return HttpStatus.CREATED;
    }
    @DeleteMapping("/deleteProduct/{id}")
    public HttpStatus deleteProductById(@PathVariable(value = "id") int id) {
        if (productService.deleteProductById(id))
            return HttpStatus.OK;
        return HttpStatus.CREATED;
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable(value="name") String productName) {
        List<Product> p = productService.getProductByName(productName);
        if (p != null)
            return new ResponseEntity<>(p, HttpStatus.OK);
        return new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
    }
//to buy the cartitems i.e when user clicks on checkout
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> checkout(@PathVariable(name="isSingleProductCheckout") boolean isSingleProductCheckout,@PathVariable(name="productId") int productId){
        return productService.getProductDetails(isSingleProductCheckout,productId);
    }
}
