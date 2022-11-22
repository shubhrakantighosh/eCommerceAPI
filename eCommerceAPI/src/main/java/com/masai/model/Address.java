package com.masai.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;
    private String pinCode;
    private String city;
    private String state;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "address")
    private User user;

}
