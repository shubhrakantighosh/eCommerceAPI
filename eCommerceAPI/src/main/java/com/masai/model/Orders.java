package com.masai.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String orderId;
    private Double totalPrice;
    private String shipping_Charges;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String pinCode;
    private String city;
    private String state;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orders")
    private List<OrderedProduct>products=new ArrayList<>();

}
