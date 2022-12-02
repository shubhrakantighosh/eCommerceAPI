package com.masai.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String userName;
    private String pinCode;
    private String city;
    private String state;

}
