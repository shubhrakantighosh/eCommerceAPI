package com.masai.services;


import com.masai.model.*;
import com.masai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private ProductRepository productRepository;

    public User addUser(User user){

        return userRepository.save(user);
    }

    public Address addAddress(Address address){

        address.setUser(userRepository.findById(address.getUserId()).get());

        return addressRepository.save(address);
    }

    public Cart addToCart(Cart cart){

        cart.setUser(userRepository.findById(cart.getUserId()).get());
        cart.setAddress(addressRepository.findById(cart.getAddressId()).get());
        cart.getProducts().add(productRepository.findById(cart.getProductId()).get());

        return cartRepository.save(cart);
    }

    public OrderStatus orderCreated(OrderStatus orderStatus){

        orderStatus.setCart(cartRepository.findById(orderStatus.getCartId()).get());
        return orderStatusRepository.save(orderStatus);
    }


}
