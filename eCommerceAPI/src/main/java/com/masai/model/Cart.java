package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cardId;
    @Transient
    private Integer productId;
    @Transient
    private Integer addressId;
    @Transient
    private Integer userId;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
    private List<Product> products=new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "cart")
    private  Address address;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderStatus orderStatus;

}
