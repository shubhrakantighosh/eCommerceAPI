package com.masai.repository;

import com.masai.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<Orders, Integer> {
}
