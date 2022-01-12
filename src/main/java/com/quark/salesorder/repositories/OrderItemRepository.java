package com.quark.salesorder.repositories;

//import java.util.List;

//import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    //List<OrderItem> findByOrder(Order order);
}
