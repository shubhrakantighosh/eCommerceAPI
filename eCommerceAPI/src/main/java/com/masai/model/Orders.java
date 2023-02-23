package com.masai.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String orderId;
    private Double totalPrice;
    private String shipping_Charges;
    @Enumerated(EnumType.STRING)
    private Payment payment;
//    @ManyToOne(cascade = CascadeType.ALL)
//    private User user;
    private Integer userId;
    private String pinCode;
    private String city;
    private String state;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orders")
    private List<OrderedProduct>products=new ArrayList<>();

}
