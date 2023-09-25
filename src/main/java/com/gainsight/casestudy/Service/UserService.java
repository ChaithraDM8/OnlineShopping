package com.gainsight.casestudy.Service;

import com.gainsight.casestudy.Entity.User;
import com.gainsight.casestudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //    public boolean addUser(User user){
//      User user1=  userRepository.save(user);
//        System.out.println(user1.getUserName()+" "+user1.getPassword());
//        if(user==null) throw new RuntimeException("User Already Present");
//      return user1!=null;
//    }
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public boolean addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

          User  u = userRepository.findById(user.getUserName()).orElse(null);
            if (u != null) throw new RuntimeException("User Already Present");
        u=userRepository.save(user);
        return u != null;
    }


    public boolean getUser(String userName, String password) {
        User user1 = userRepository.findById(userName).get();
        System.out.println(user1.getPassword());
        if (passwordEncoder.matches(password,user1.getPassword()))
          return true;
         else throw new RuntimeException("User Not Found");

    }
}


