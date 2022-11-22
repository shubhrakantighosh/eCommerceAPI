package com.masai.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Address>addresses=new HashSet<>();
    @OneToMany(cascade =CascadeType.ALL,mappedBy = "user")
    private Set<Cart>carts=new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<UserSession>userSessions=new ArrayList<>();


}
