package com.masai.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer categoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

}
