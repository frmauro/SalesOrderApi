package com.quark.salesorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.quark.salesorder.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByuserId(String userId);
}
