package com.masai.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @Transient
    private Integer cartId;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "orderStatus")
    private Cart cart;
    @Embedded
    private Payment payment;


}
