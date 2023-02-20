package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderedProductId;
    private String productName;
    private Double productPrice;
    private Integer gst;
    private Double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders orders;

}
