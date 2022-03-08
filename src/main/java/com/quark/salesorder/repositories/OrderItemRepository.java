package com.quark.salesorder.repositories;

import java.util.List;

import com.quark.salesorder.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = "select i.id, i.description, i.price, i.product_id, i.quantity from tb_order_item i inner join order_orderitem oi on i.id = oi.orderitem_id  where oi.order_id = ?1", nativeQuery = true)
    //@Query(value = "select * from tb_order_item i inner join order_orderitem oi on i.id = oi.order_id where oi.order_id = ?1 and oi.orderitem_id = ?1", nativeQuery = true)
    //@Query(value = "select * from tb_order_item i inner join order_orderitem oi where oi.order_id = ?1", nativeQuery = true)
    List<OrderItem> findByOrderId(Integer id);
   //      SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1 
}
