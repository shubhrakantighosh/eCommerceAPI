package com.masai.services;


import com.masai.model.Address;
import com.masai.model.User;
import com.masai.repository.AddressRepository;
import com.masai.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public User addUser(User user){

        return userRegistrationRepository.save(user);
    }

    public Address addAddress(Address address){

        address.setUser(userRegistrationRepository.findById(address.getUserId()).get());

        return addressRepository.save(address);
    }

}
