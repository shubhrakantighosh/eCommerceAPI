package com.masai.DTO;

import com.masai.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {

    private String orderId;
    private Double totalPrice;
    private String shipping_Charges;
    private Payment payment;

}
