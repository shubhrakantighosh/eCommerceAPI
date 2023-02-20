package com.masai.repository;

import com.masai.model.Product;
import com.masai.DTO.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(" select new com.masai.DTO.ProductDTO (product.productId ,product.productName, product.productPrice) from Product product ")
    List<ProductDTO> products();

    @Query(" select new com.masai.DTO.ProductDTO  (product.productId ,product.productName, product.productPrice) from Product product where product.category.categoryId=?1 ")
    List<ProductDTO> productsSearchByCategoryId(Integer categoryId);

    @Query(" select new com.masai.DTO.ProductDTO (product.productId ,product.productName, product.productPrice) from Product product where product.productPrice>=?1 AND product.productPrice<=?2")
    List<ProductDTO> searchByProductPrice(Double minprice,Double maxprice);

    @Query("select new com.masai.DTO.ProductDTO (product.productId,product.productName,product.productPrice) from Product product where product.productName=?1")
    ProductDTO viewProductByName(String name);
}
