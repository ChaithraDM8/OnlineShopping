package com.gainsight.casestudy.Repository;

import com.gainsight.casestudy.Entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Integer> {

    TransactionDetails findByOrderId(int orderId);
}
