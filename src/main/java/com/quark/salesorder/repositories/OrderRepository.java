package com.quark.salesorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quark.salesorder.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
