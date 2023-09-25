package com.gainsight.casestudy.Repository;

import com.gainsight.casestudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    boolean findByUserName(String userName);


}
