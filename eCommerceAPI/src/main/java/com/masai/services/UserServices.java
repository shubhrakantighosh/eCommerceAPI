package com.masai.services;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.UserException;
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
    @Autowired
    private CategoryRepository categoryRepository;

    public User registerUser(User user) throws UserException {
        boolean flag=true;

        List<User>users=userRepository.findAll();

        for(User i:users){

            if(i.getUserName().equalsIgnoreCase(user.getUserName())
            && i.getUserPassword().equals(user.getUserPassword())){
                flag=false;
            }

        }

        if(flag){

            return userRepository.save(user);
        }else
            throw new UserException("You already registered.");
    }

    public String logIn(String username, String password) throws UserException {

        boolean flag=true;

        List<User> users=userRepository.findAll();

        for (User i:users){

            if(i.getUserName().equalsIgnoreCase(username) &&
            i.getUserPassword().equals(password)){
                flag=false;

            }
        }

        if (flag){
            throw new UserException("You are not registered with us.");

        }else
            return "LogIn successful.";

    }


    public List<Category> categories() throws CategoryException {

        List<Category>categories=categoryRepository.findAll();

        if(categories.size()==0){
            throw new CategoryException("No category exists.");
        }else
            return categories;
    }

    public List<Product> searchByCategory(String categoryName){

        return null;

    }

    public Address addAddress(Address address){

        address.setUser(userRepository.findById(address.getUserId()).get());

        return addressRepository.save(address);
    }

    public Cart addToCart(Cart cart){

        cart.setUser(userRepository.findById(cart.getUserId()).get());
        cart.setAddress(addressRepository.findById(cart.getAddressId()).get());
        cart.getProducts().add(productRepository.findById(cart.getProductId()).get());

        Product product=productRepository.findById(cart.getProductId()).get();
        product.getCarts().add(cart);
        productRepository.save(product);

        Address address=addressRepository.findById(cart.getAddressId()).get();
        address.getCarts().add(cart);
        addressRepository.save(address);

        return cartRepository.save(cart);
    }

    public Orders orderCreated(Orders orders){
        orders.setCart(cartRepository.findById(orders.getCartId()).get());

        Cart cart=cartRepository.findById(orders.getCartId()).get();
        cart.setOrders(orders);

        return orderStatusRepository.save(orders);
    }


}
