package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @Transient
    private Integer productId;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products=new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
