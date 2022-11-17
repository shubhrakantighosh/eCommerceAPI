package com.masai.controller;


import com.masai.model.*;
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

    @GetMapping("/category/{name}")
    public Category searchByCategory(@PathVariable String name){
        return null;
    }


    @GetMapping("/products")
    public List<Product> viewAllProducts(){
        return null;
    }

    @GetMapping("/product/{id}")
    public Product viewProductById(@PathVariable Integer id){
        return null;
    }

    @PostMapping("/cart")
    public Cart addToCart(@RequestBody Cart cart){
        return userServices.addToCart(cart);
    }

    @PostMapping("/orderStatus")
    public OrderStatus createOrder(@RequestBody OrderStatus orderStatus){
        return userServices.orderCreated(orderStatus);
    }

    @PostMapping("/addaddress")
    public Address addAddress(@RequestBody Address address){
        return userServices.addAddress(address);
    }


}
