package com.quark.salesorder.services;

import java.util.List;
import java.util.Optional;

import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;
import com.quark.salesorder.repositories.OrderItemRepository;
import com.quark.salesorder.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemService {
    
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemrepository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public List<Order> findByUserId(String userId){
        return repository.findByuserId(userId);
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }
    
    public Order insert(Order obj) {
		return repository.save(obj);
    }
    
    public Order update(Order entity) {
			return repository.save(entity);
	}

    public List<OrderItem> insertOrderItemAll(List<OrderItem> items) {
		return orderItemrepository.saveAll(items);
	}

    public OrderItem insertOrderItem(OrderItem item) {
            return orderItemrepository.save(item);
	}
}
