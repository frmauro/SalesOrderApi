package com.quark.salesorder.repositories;

import com.quark.salesorder.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
}
