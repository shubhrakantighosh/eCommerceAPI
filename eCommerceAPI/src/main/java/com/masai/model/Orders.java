package com.masai.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @Transient
    private Integer cartId;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "orders")
    private Cart cart;
    @Enumerated(EnumType.STRING)
    private Payment payment;


}
