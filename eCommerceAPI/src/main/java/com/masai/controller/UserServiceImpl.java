package com.masai.controller;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.*;
import com.masai.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserServiceImpl {

    @Autowired
    private UserServices userServices;


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserException {
        return new ResponseEntity<>(userServices.registerUser(user),HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestParam(value = "username") String username,
                                        @RequestParam(value = "password") String password) throws UserException {

        return new ResponseEntity<>(userServices.logIn(username,password), HttpStatus.ACCEPTED);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() throws UserException {
        return new ResponseEntity<>(userServices.logout(),HttpStatus.OK);
    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> categories() throws CategoryException {
        return new ResponseEntity<>(userServices.categories(),HttpStatus.OK);
    }


    @GetMapping("/category/{name}")
    public Category searchByCategory(@PathVariable String name){
        return null;
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> product() throws ProductException {
        return new ResponseEntity<>(userServices.products(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public Product viewProductById(@PathVariable Integer id){
        return null;
    }

    @PostMapping("/cart")
    public Cart addToCart(@RequestBody Cart cart) throws UserException {
        return userServices.addToCart(cart);
    }

    @PostMapping("/order")
    public Orders createOrder(@RequestBody Orders orders){
        return userServices.orderCreated(orders);
    }

    @PostMapping("/address")
    public String addAddress(@RequestBody Address address) throws UserException {
        return userServices.addAddress(address);
    }


}
