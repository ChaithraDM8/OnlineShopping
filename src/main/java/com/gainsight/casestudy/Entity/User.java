package com.gainsight.casestudy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    private String userName;
    private String password;
    private String roles;

     @OneToMany(mappedBy = "user",fetch= FetchType.EAGER)
     @JsonIgnore
    //one row of user is associeted with many rown in cart
    List<Cart> cartItems;
public  User(){}

}
