package com.masai.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(cascade =CascadeType.ALL,mappedBy = "user")
    private List<Cart>carts=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<UserSession>userSessions=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Orders> orders =new ArrayList<>();

}
