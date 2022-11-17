package com.masai.repository;

import com.masai.model.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
