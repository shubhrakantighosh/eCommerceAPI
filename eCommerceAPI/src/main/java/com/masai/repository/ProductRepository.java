package com.masai.repository;

import com.masai.model.Product;
import com.masai.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(" select new com.masai.model.ProductDTO (product.productId ,product.productName, product.productPrice) from Product product ")
    List<ProductDTO> products();

}
