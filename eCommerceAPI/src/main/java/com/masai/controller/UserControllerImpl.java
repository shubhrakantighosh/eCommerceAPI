package com.masai.controller;


import com.masai.DTO.CategoryDTO;
import com.masai.DTO.OrderDTO;
import com.masai.DTO.OrderedProductDTO;
import com.masai.DTO.ProductDTO;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.*;
import com.masai.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllerImpl {

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

    @PostMapping("/add/address")
    public ResponseEntity<String> addAddress(@RequestBody Address address) throws UserException {
        return new ResponseEntity<>(userServices.addAddress(address),HttpStatus.OK);
    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> categories() throws CategoryException {
        return new ResponseEntity<>(userServices.categories(),HttpStatus.OK);
    }


    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDTO>> searchByCategory(@PathVariable String categoryName) throws CategoryException, ProductException {
        return new ResponseEntity<>(userServices.searchByCategoryName(categoryName),HttpStatus.OK);
    }

    @GetMapping("/products/{min}/{max}")
    public ResponseEntity<List<ProductDTO>> searchByProductPrice(@PathVariable Double min,@PathVariable Double max) throws ProductException, UserException {
        return new ResponseEntity<>(userServices.searchByProductPrice(min,max),HttpStatus.OK);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> product() throws ProductException {
        return new ResponseEntity<>(userServices.products(), HttpStatus.OK);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<ProductDTO> viewProductByName(@PathVariable String name) throws ProductException {
        return new ResponseEntity<>(userServices.viewProductByName(name),HttpStatus.OK);
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Integer productId) throws UserException {
        return new ResponseEntity<>(userServices.addToCart(productId),HttpStatus.OK);
    }

    @GetMapping("/cart/amount")
    private ResponseEntity<String> totalAmount() throws UserException {
        return new ResponseEntity<>(userServices.totalAmount(),HttpStatus.OK);
    }

    @DeleteMapping("/cart/remove")
    public ResponseEntity<String> removeCart() throws UserException, CategoryException {
        return new ResponseEntity<>(userServices.removeCart(),HttpStatus.OK);
    }

    @PostMapping("/order/place/{paymentMode}")
    public ResponseEntity<String> createPlace(@PathVariable Payment paymentMode) throws UserException, CategoryException {
        return new ResponseEntity<>(userServices.orderPlace(paymentMode),HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity <HashMap<OrderDTO, List<OrderedProductDTO>>> orderSummary() throws UserException, ProductException {
        return new ResponseEntity<>(userServices.orderSummary(),HttpStatus.OK);
    }

    @PutMapping("/update/address/{pinCode}/{city}/{state}")
    public ResponseEntity<String> updateAddress(@PathVariable String pinCode,@PathVariable String city,@PathVariable String state) throws UserException {
        return new ResponseEntity<>(userServices.updateAddress(pinCode,city,state),HttpStatus.OK);
    }

}
