package com.masai.repository;

import com.masai.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewProductRepository extends JpaRepository<Product, Integer> {
}
