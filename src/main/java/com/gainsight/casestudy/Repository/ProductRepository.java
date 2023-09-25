package com.gainsight.casestudy.Repository;

import com.gainsight.casestudy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {
    List<Product> findByProductName(String productName);
}
