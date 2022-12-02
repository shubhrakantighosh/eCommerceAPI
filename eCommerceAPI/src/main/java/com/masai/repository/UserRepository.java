package com.masai.repository;

import com.masai.model.User;
import com.masai.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new com.masai.model.UserDTO(user.userId,user.userName,user.address.pinCode,user.address.city,user.address.state) from User user")
    List<UserDTO> users();

    @Query("select new com.masai.model.UserDTO(user.userId,user.userName,user.address.pinCode,user.address.city,user.address.state) from User user where user.userId=?1")
    UserDTO searchByUserId(Integer userId);
    @Query("select new com.masai.model.UserDTO(user.userId,user.userName,user.address.pinCode,user.address.city,user.address.state) from User user where user.userName=?1")
    UserDTO searchByUserName(String userName);

}