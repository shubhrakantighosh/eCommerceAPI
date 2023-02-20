package com.masai.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderedProductDTO {

    private Integer orderedProductId;
    private String productName;
    private Double productPrice;
    private Integer gst;
    private Double totalPrice;
}
