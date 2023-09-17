package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.ImageModel;
import com.gainsight.casestudy.Entity.Product;
import com.gainsight.casestudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

   // @PostMapping(value={"/addProduct"},consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostMapping(value={"/addProduct"})
    public HttpStatus addProduct(@RequestParam Product product) throws IOException {//, @RequestPart("imageFile")MultipartFile file) {
//    @PostMapping(value={"/addProduct"})
//    public HttpStatus addProduct(MultipartFile file,Product product) throws IOException {
//        try{
//            ImageModel images =uploadImage(file);
//            product.setProductImages(images);
//            if (productService.addOrModifyProduct(product))
//                return HttpStatus.OK;
//        return HttpStatus.CREATED;
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
            if (productService.addOrModifyProduct(product))
               return HttpStatus.OK;
        return HttpStatus.CREATED;
        }

public ImageModel uploadImage(MultipartFile file) throws IOException {
   ImageModel images=new ImageModel();
    //for(MultipartFile file:multipartFiles){
        ImageModel imageModel=new ImageModel(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes());
        //images.add(imageModel);
   // }
    //return images;
    return imageModel;
}
    @PutMapping("/modifyProduct")
    public HttpStatus modifyProduct(MultipartFile file,@RequestBody Product product) throws IOException {
        if (productService.addOrModifyProduct(product))
            return HttpStatus.OK;
        return HttpStatus.CREATED;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable(value = "id") int id) {
        if (productService.deleteProductById(id))
            return HttpStatus.OK;
        return HttpStatus.CREATED;
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable(value="name") String productName) {
        Product p = productService.getProductByName(productName);
        if (p != null)
            return new ResponseEntity<>(p, HttpStatus.OK);
        return new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
    }
}
