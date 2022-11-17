package com.masai.controller;


import com.masai.model.Category;
import com.masai.model.User;
import com.masai.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserServiceImpl {

    @Autowired
    private UserServices userServices;


    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return userServices.addUser(user);
    }

    @GetMapping("/categories")
    public List<Category> categories(){
        return null;
    }

    public Category searchByCategory(){
        return null;
    }


}
