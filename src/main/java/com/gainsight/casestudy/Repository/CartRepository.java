package com.gainsight.casestudy.Repository;

import com.gainsight.casestudy.Entity.Cart;
import com.gainsight.casestudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
public List<Cart> findByUser(User user);
}
