package com.gainsight.casestudy.Repository;

import com.gainsight.casestudy.Entity.OrderDetails;
import com.gainsight.casestudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    public List<OrderDetails> findByUser(User user);

    public List<OrderDetails> findByOrderStatus(String status);
}
