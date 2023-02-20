package com.masai.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private Double productPrice;
    @Transient
    private Integer categoryId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Cart>carts=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Images>images=new HashSet<>();

}
