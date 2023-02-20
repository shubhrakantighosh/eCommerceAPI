package com.masai.repository;

import com.masai.DTO.OrderedProductDTO;
import com.masai.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct,Integer> {

    @Query("select new com.masai.DTO.OrderedProductDTO (product.orderedProductId,product.productName,product.productPrice,product.gst,product.totalPrice) from OrderedProduct product where product.orders.orderId=?1")
    List<OrderedProductDTO>orders(String orderId);

}
