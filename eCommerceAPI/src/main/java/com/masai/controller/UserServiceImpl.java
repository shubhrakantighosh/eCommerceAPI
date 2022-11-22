package com.masai.controller;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.UserException;
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


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws UserException {
        return userServices.registerUser(user);
    }

    @GetMapping("/login")
    public String logIn(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) throws UserException {
        return userServices.logIn(username,password);
    }

    @GetMapping("/logout")
    public String logout() throws UserException {
        return userServices.logout();
    }


    @GetMapping("/categories")
    public List<Category> categories() throws CategoryException {
        return userServices.categories();
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
    public Orders createOrder(@RequestBody Orders orders){
        return userServices.orderCreated(orders);
    }

    @PostMapping("/addaddress")
    public Address addAddress(@RequestBody Address address){
        return userServices.addAddress(address);
    }


}
