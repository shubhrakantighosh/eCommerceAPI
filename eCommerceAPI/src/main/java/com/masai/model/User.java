package com.masai.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Address>addresses=new HashSet<>();

}
