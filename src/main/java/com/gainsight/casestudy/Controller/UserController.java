package com.gainsight.casestudy.Controller;

import com.gainsight.casestudy.Entity.User;
import com.gainsight.casestudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("Users")
public class UserController {

 public static String USERNAME;
    @Autowired
    UserService userService;

        @PostMapping("addUser")
        public HttpStatus addUsers(@RequestBody User user)
        {
            if(userService.addUser(user))
                return HttpStatus.OK;
            return HttpStatus.NOT_MODIFIED;
        }

       // @GetMapping("/login")
    public String login( @RequestParam String  userName, @RequestParam String password){
            if(userService.getUser(userName,password)) {

                return "User Succesfully logged in";
            }
           else  throw new RuntimeException("User not Found");
        }

   // @GetMapping("/username")
   @GetMapping("/login")
    public String currentUserName(Authentication authentication) {
            USERNAME=authentication.getName();
            //System.out.println(authentication);
        return "User Succesfully logged in";
    }
    }


