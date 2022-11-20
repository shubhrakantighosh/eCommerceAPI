package com.masai.model;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Data
@ToString
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
    @NotNull
    private Payment payment;


}
