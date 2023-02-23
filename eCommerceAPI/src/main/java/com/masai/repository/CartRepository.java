package com.masai.repository;

import com.masai.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from cart_products where carts_cart_id=:id")
    void delete_product_cart(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from cart where cart_id=:id")
    void delete_cart(Integer id);

}
